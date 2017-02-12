package com.yairkukielka.ticker.domain

import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.data.CurrenciesDataModelResponses
import com.yairkukielka.ticker.data.TickerDataModelResponse
import com.yairkukielka.ticker.data.TickerDataModelResponses
import com.yairkukielka.ticker.data.TickerModelResponse

/**
 * Created by Yair Kukielka on 10/14/16.
 */
class Prices(val api: Api) {

    var bitcoinPrice = 0

    init {
//        val list = api.currencies().
//        val list = CurrenciesDataModelResponses::class.members // the members are getBTC() and so on
//                .filter { it.name.contains('_') }
//                .map { TickerModelResponse(it.name, it.call(item) as TickerDataModelResponse) }
    }

}