package com.yairkukielka.ticker.domain

import android.util.Log
import com.yairkukielka.ticker.api.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers;

class HomePresenter(val api: Api) {

    fun tick() {
        api.ticker().subscribeOn(Schedulers.io())
                .doOnNext {
                    Log.d("last ", it.BTC_ETH.last)
                }
                .doOnError {
                    Log.d("TAG", it.message)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Log.d("TAG", it.BTC_ETH.last) },
                        { Log.d("TAG", it.message) })
    }

    fun getCurrencies() {
        api.currencies().subscribeOn(Schedulers.io())
                .doOnNext {
                    Log.d("last ", it.currenciesMap.values.toString())
                }
                .doOnError {
                    Log.d("TAG", it.message)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Log.d("TAG", it.currenciesMap.toString()) },
                        { Log.d("TAG", it.message) })
    }
}