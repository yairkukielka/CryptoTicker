package com.yairkukielka.ticker.adapter

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.yairkukielka.ticker.data.CurrenciesDataModelResponse
import com.yairkukielka.ticker.data.CurrenciesDataModelResponses
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.CurrencyResponse
import org.json.JSONObject
import java.util.*

/**
 * Created by superyair on 10/15/16.
 */
class CurrencyMapJsonAdapter {

    @ToJson fun toJson(card: CurrenciesMap): String {
        return card.toString()
    }

    @FromJson
    fun fromJson(root : JSONObject) : CurrenciesMap {
//        if (card.length() != 2) throw new JsonDataException("Unknown card: " + card);
        Log.d("MYTAG", root.toString())
        var map = HashMap<String, String>()
//
//        val root = JSONObject(root)
        val keys = root.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            if (root.get(key) is JSONObject) {
                map.put(key, root.getString("name"))
            }
        }
        return CurrenciesMap(map)
    }
}