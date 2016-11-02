package com.yairkukielka.ticker.data

import com.yairkukielka.ticker.domain.CurrencyInfo


class TickerModelResponse(val currency1: CurrencyInfo, val currency2: CurrencyInfo, val data: TickerDataModelResponse) {
//    val id: Int
    val last: String
//    val lowestAsk: String
//    val highestBid: String
//    val percentChange: String
//    val baseVolume: String
//    val quoteVolume: String
//    val high24hr: String
//    val low24hr: String

    init {
//        id = data.id
        last = data.last
//        lowestAsk = data.lowestAsk
//        highestBid = data.highestBid
//        percentChange = data.percentChange
//        baseVolume = data.baseVolume
//        quoteVolume = data.quoteVolume
//        high24hr = data.high24hr
//        low24hr = data.low24hr
    }

}