package com.yairkukielka.ticker.api

import com.yairkukielka.ticker.data.CurrenciesDataModelResponses
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.TickerDataModelResponses
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Observable
import retrofit2.Call

interface TickerService2 {

    //?command=returnCurrencies
    @GET("/public?command=returnCurrencies") fun getCurrencies(): Call<CurrenciesMap>
}
