package com.yairkukielka.ticker.adapter

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.domain.CurrencyInfo
import java.util.*

/**
 * Created by Yair Kukielka on 10/15/16.
 */
class CurrencyMapJsonAdapter {

    @ToJson fun toJson(c: CurrenciesMap): String {
        return "fake CurrenciesMap to Json in CurrencyMapJsonAdapter"
    }

    @FromJson
    fun fromJson(jsonReader: JsonReader): CurrenciesMap {
        val currenciesMap = HashMap<String, CurrencyInfo>()
        try {
            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                readCurrencyObject(currenciesMap, jsonReader)
            }
            jsonReader.endObject()
        } catch(e: Exception) {
            Log.e("CurrencyMapJsonAdapter", e.toString())
        } finally {
            jsonReader.close();
        }

        return CurrenciesMap(currenciesMap)
    }

    private fun readCurrencyObject(currenciesMap: HashMap<String, CurrencyInfo>, jsonReader: JsonReader) {
        val symbol = jsonReader.nextName()
        jsonReader.beginObject()
        var disabled = 0
        var delisted = 0
        var frozen = 0
        var name: String = ""
        while (jsonReader.hasNext()) {
            val n = jsonReader.nextName()
            when (n) {
                "name" -> name = jsonReader.nextString()
                "disabled" -> disabled = jsonReader.nextInt()
                "delisted" -> delisted = jsonReader.nextInt()
                "frozen" -> frozen = jsonReader.nextInt()
                else -> {
                    jsonReader.skipValue()
                }
            }
        }
        if (disabled == 0 && delisted == 0 && frozen == 0) {
            currenciesMap.put(symbol, CurrencyInfo(symbol, name))
        }
        jsonReader.endObject()
    }

}