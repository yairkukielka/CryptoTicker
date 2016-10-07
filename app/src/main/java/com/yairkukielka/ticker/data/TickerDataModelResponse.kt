package com.yairkukielka.ticker.data


class TickerDataModelResponse(val id: Int, val last: String, val lowestAsk: String, val highestBid: String,
                              val percentChange: String, val baseVolume: String, val quoteVolume: String,
                              val high24hr: String, val low24hr: String) {
}