package com.yairkukielka.ticker.domain

import android.util.Log
import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.data.CurrenciesMap
import com.yairkukielka.ticker.data.TickerDataModelResponses
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class HomePresenter(val api: Api) {

    val b : BiFunction<TickerDataModelResponses, CurrenciesMap, String> = BiFunction { x, y -> "a" }

    fun tick() {
//        Observable.range(1, 2).flatMap {
//
//            val tickerDataResponses = api.ticker()
//            val curr = getCurrencies()
//
//            tickerDataResponses
//        }
        api.ticker()
                .withLatestFrom(getCurrencies(), b)
                .subscribeOn(Schedulers.io())
                .doOnNext {
//                    Log.d("last ", it.BTC_ETH.last)
                }
                .doOnError {
//                    Log.d("TAG", it.message)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Log.d("TAG", toString()) },
                        { Log.d("TAG", toString()) })
    }

    fun getCurrencies(): Observable<CurrenciesMap> {
        return api.currencies()
//                .subscribeOn(Schedulers.io())
//                .doOnNext {
//                    Log.d("last ", it.currenciesMap.toString())
//                }
//                .doOnError {
//                    Log.d("TAG", it.message)
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { Log.d("TAG", it.toString()) },
//                        { Log.d("TAG", it.message) })
    }


}