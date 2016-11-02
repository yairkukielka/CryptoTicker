package com.yairkukielka.ticker.api

import com.jakewharton.retrofit2.adapter.rxjava2.Result
import com.yairkukielka.ticker.data.CurrenciesDataModelResponses
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.TickerDataModelResponses
import io.reactivex.Observable

class Api(private val tickerService: TickerService) {

    companion object {
        const val BASE_URL: String = "https://poloniex.com/"
        const val RETURN_TICKER_QUERY: String = "returnTicker"
        const val RETURN_CURRENCIES_QUERY: String = "returnCurrencies"
        const val RETURN_24H_VOLUME_QUERY: String = "return24Volume"
        const val RETURN_CHART_QUERY: String = "returnChartData"
    }

    fun ticker(): Observable<TickerDataModelResponses> {
        return tickerService.getTicker(RETURN_TICKER_QUERY)
    }
    fun currencies(): Observable<CurrenciesMap> {
        return tickerService.getCurrencies(RETURN_CURRENCIES_QUERY)
    }
    fun volume24h(): Observable<TickerDataModelResponses> {
        return tickerService.getTicker(RETURN_24H_VOLUME_QUERY)
    }
    fun chart(): Observable<TickerDataModelResponses> {
        return tickerService.getTicker(RETURN_CHART_QUERY)
    }
}