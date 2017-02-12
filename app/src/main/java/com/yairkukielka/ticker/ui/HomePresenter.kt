package com.yairkukielka.ticker.domain

import android.util.Log
import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.data.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class HomePresenter(val api: Api) {

    companion object {
        val composer: BiFunction<TickerDataModelResponses, CurrenciesMap, TickerModelResponses> = BiFunction { x, y -> transformToTickerModelResponses(x, y) }

        fun transformToTickerModelResponses(tickerDataModelResponses: TickerDataModelResponses, currenciesMap: CurrenciesMap): TickerModelResponses {
            val list: MutableList<TickerModelResponse> = mutableListOf()
            tickerDataModelResponses.responses
                    .map {
                        var result: TickerModelResponse? = null
                        var split = it.pairName.split("_")
                        var symbol1 = split.get(0)
                        if (symbol1.equals("USDT")) {
                            // only use usdt because we're interested only in comparing with US dollar
                            var currency1 = currenciesMap.map[symbol1]
                            var symbol2 = split.get(1)
                            var currency2 = currenciesMap.map[symbol2]
                            if (currency1 != null && currency2 != null) {
                                result = TickerModelResponse(currency1, currency2, it.last)
                            }
                        }
                        result
                    }
                    .filterNotNullTo(list)

            return TickerModelResponses(list)
        }
    }

    fun tick(): Observable<List<CurrencyItem>> {//Observable<TickerModelResponses> {//
//        Observable.range(1, 2).flatMap {
//
//            val tickerDataResponses = api.ticker()
//            val curr = getCurrencies()
//
//            tickerDataResponses
//        }

        return Observable.interval(0L, 3L, TimeUnit.SECONDS)
                .take(14)
                .concatMap { it -> api.ticker() }
                .withLatestFrom(getCurrencies(), composer)
                .map { it -> it.list.map { CurrencyItem(it.currency2.name, it.last) } } // fix. Translate to List<CurrencyItem> properly
                .subscribeOn(Schedulers.io())
                .doOnNext {
//                    Log.d("last ", it.responses.BTC_ETH.last)
                }
                .doOnError {
                    Log.d("TAG", it.message)
                }
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { Log.d("TAG", toString()) },
//                        { Log.d("TAG", toString()) })
    }


//    fun getCurrencies(): BehaviorSubject<CurrenciesMap> {
//        val map: CurrenciesMap = CurrenciesMap(mapOf(Pair("BTC", CurrencyInfo("BTC", "Bitcoin")), Pair("ETH", CurrencyInfo("ETH", "Ethereum"))))
//        return BehaviorSubject.createDefault(map)
//    }

    fun getCurrencies(): Observable<CurrenciesMap> {
        return api.currencies()
                .subscribeOn(Schedulers.io())
                .doOnNext {
//                    Log.d("last ", it.currenciesMap.toString())
                }
                .doOnError {
//                    Log.d("TAG", it.message)
                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { Log.d("TAG", it.toString()) },
//                        { Log.d("TAG", it.message) })
    }


}