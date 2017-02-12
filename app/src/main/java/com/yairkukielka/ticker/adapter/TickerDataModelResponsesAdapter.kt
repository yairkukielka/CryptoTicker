package com.yairkukielka.ticker.adapter

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson
import com.yairkukielka.ticker.data.*
import org.json.JSONObject
import java.util.*

/**
 * Created by Yair Kukielka on 10/15/16.
 */
class TickerDataModelResponsesAdapter {

    @ToJson fun toJson(t: TickerDataModelResponses): String {
        return "fake TickerDataModelResponses to Json in TickerDataModelResponsesAdapter"
    }

    @FromJson
    fun fromJson(jsonReader: JsonReader): TickerDataModelResponses {
        val list = ArrayList<TickerDataModelResponse>()
        try {
            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                readTickerDataModelResponse(list, jsonReader)
            }
            jsonReader.endObject()
        } catch(e: Exception) {
            Log.e("TickerDataModelRespons", e.toString())
        } finally {
            jsonReader.close();
        }

        return TickerDataModelResponses(list)
    }

    private fun readTickerDataModelResponse(list: MutableList<TickerDataModelResponse>, jsonReader: JsonReader) {
//        val id: Int, val last: String, val lowestAsk: String, val highestBid: String,
//        val percentChange: String, val baseVolume: String, val quoteVolume: String,
//        val high24hr: String, val low24hr: String
        val pairName = jsonReader.nextName()
        jsonReader.beginObject()
        var last = ""
        var lowestAsk = ""
        var highestBid = ""
        var percentChange = ""
        var baseVolume = ""
        var quoteVolume = ""
        var high24hr = ""
        var low24hr = ""
        var currency1: String = ""
        var currency2: String = ""
        while (jsonReader.hasNext()) {
            val n = jsonReader.nextName()
            when (n) {
                "last" -> last = jsonReader.nextString()
//                "lowestAsk" -> lowestAsk = jsonReader.nextInt()
//                "highestBid" -> highestBid = jsonReader.nextInt()
//                "percentChange" -> percentChange = jsonReader.nextInt()
                else -> {
                    jsonReader.skipValue()
                }
            }
        }
//        if (disabled == 0 && delisted == 0 && frozen == 0) {
//            list.add(item)
//        }
        var item = TickerDataModelResponse(pairName, last)
        list.add(item)
        jsonReader.endObject()
    }
//    CurrenciesMap
//    @FromJson
//    fun fromJson(r : String) : TickerDataModelResponses {
////        if (card.length() != 2) throw new JsonDataException("Unknown card: " + card);
////        Log.d("MYTAG", currency)
//
//    val item1 = CurrenciesDataModelResponse("BTC", "Bitcoin")
//    val item2 = CurrenciesDataModelResponse("ETH", "Ethereum");
//    val item3 = CurrenciesDataModelResponse("LTC", "Litecoin");
//    return CurrenciesDataModelResponses(item1, item2, item3)
//    }

//    @FromJson
//    fun fromJson(currency : String) : CurrenciesMap {
////        if (card.length() != 2) throw new JsonDataException("Unknown card: " + card);
//        Log.d("MYTAG", currency)
//        var map = HashMap<String, String>()
//
//        val root = JSONObject(currency)
//        val keys = root.keys()
//        while (keys.hasNext()) {
//            val key = keys.next()
//            if (root.get(key) is JSONObject) {
//                map.put(key, root.getString("name"))
//            }
//        }
//        return CurrenciesMap(map)
//    }
}