package com.yairkukielka.ticker.domain

import android.util.Log
import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.data.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
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

    fun tick(): Observable<List<CurrencyItem>> {
        return Observable.interval(0L, 3L, TimeUnit.SECONDS)
                .take(20)
                .concatMap { api.ticker() }
                .withLatestFrom(getCurrencies(), composer)
                .map { it -> it.list.map { CurrencyItem(it.currency2.name, it.last) } }
                .subscribeOn(Schedulers.io())
                .doOnNext {}
                .doOnError {
                    Log.d("TAG", it.message)
                }
                .scan(compareWithPreviousList)
    }

    val compareWithPreviousList: BiFunction<List<CurrencyItem>, List<CurrencyItem>, List<CurrencyItem>> =
            BiFunction { x, y -> compareWithPrevious(x, y) }

    fun compareWithPrevious(x: List<CurrencyItem>, y: List<CurrencyItem>): List<CurrencyItem> {
        if (x.size != y.size) {
            return y
        } else {
            for (i in x.indices) {
                if (x[i].currencyName.equals(y[i].currencyName)) {
                    val xInt = x[i].currencyValue.toFloat()
                    val yInt = y[i].currencyValue.toFloat()

                    if (xInt < yInt) {
                        y[i].state = STATE.INCREASED
                    } else if (xInt > yInt) {
                        y[i].state = STATE.DECREASED
                    } else {
                        y[i].state = STATE.NOT_CHANGED
                    }
                }
            }
            return y//mutableListOf<CurrencyItem>()
        }
    }

    fun getCurrencies(): Observable<CurrenciesMap> {
        return api.currencies()
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    //                    Log.d("last ", it.map.toString())
                }
                .doOnError {
                    //                    Log.d("TAG", it.message)
                }
    }


}

