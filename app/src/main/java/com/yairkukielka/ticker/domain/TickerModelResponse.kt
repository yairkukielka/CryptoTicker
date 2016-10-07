package com.yairkukielka.ticker.data

import kotlin.reflect.KProperty


class TickerModelResponse(val name: String, val data: TickerDataModelResponse) {
    val id: Int
    val last: String
    val lowestAsk: String
    val highestBid: String
    val percentChange: String
    val baseVolume: String
    val quoteVolume: String
    val high24hr: String
    val low24hr: String

    init {
        id = data.id
        last = data.last
        lowestAsk = data.lowestAsk
        highestBid = data.highestBid
        percentChange = data.percentChange
        baseVolume = data.baseVolume
        quoteVolume = data.quoteVolume
        high24hr = data.high24hr
        low24hr = data.low24hr
    }

}