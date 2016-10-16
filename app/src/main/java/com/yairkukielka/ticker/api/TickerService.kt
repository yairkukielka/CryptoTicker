package com.yairkukielka.ticker.api

import com.yairkukielka.ticker.data.CurrenciesDataModelResponses
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.TickerDataModelResponses
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Observable

interface TickerService {

    // ?command=returnTicker
    @GET("/public") fun getTicker(@Query("command") command: String): Observable<TickerDataModelResponses>

    // ?command=return24hVolume
    @GET("/public") fun get24hVolume(@Query("command") command: String): Observable<TickerDataModelResponses>

    //?command=returnCurrencies
    @GET("/public?command=returnCurrencies") fun getCurrencies(): Observable<CurrenciesMap>//@Path("command") command: String
}
