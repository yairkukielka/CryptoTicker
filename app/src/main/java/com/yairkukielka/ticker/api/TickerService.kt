package com.yairkukielka.ticker.api

import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.TickerDataModelResponses
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerService {

    // ?command=returnTicker
    @GET("/public") fun getTicker(@Query("command") command: String): Observable<TickerDataModelResponses>

    // ?command=return24hVolume
    @GET("/public") fun get24hVolume(@Query("command") command: String): Observable<TickerDataModelResponses>

    //?command=returnCurrencies
    @GET("/public") fun getCurrencies(@Query("command") command: String): Observable<CurrenciesMap>
}
