package com.yairkukielka.ticker

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.yairkukielka.ticker.adapter.CurrencyJsonAdapter
import com.yairkukielka.ticker.adapter.CurrencyMapJsonAdapter
import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.api.TickerService
import com.yairkukielka.ticker.api.TickerService2
import com.yairkukielka.ticker.data.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by superyair on 9/6/16.
 */
class CurrenciesDataModelResponsesTest() {

    //    val mapper: (TickerDataModelResponses) -> Observable<TickerModelResponse> = {
//        Observable.fromArray(
//                TickerModelResponse("BTC_ETH", it.BTC_ETH),
//                TickerModelResponse("BTC_LTC", it.BTC_LTC))
//    }
    val mapperModelResponses: (CurrenciesDataModelResponses) -> TickerModelResponses = { mapToModelResponse(it) }

    private fun mapToModelResponse(item: CurrenciesDataModelResponses): TickerModelResponses {
        // this gets the name and value of TickerDataModelResponses.BTC_ETH and creates a TickerModelResponse("BTC_ETH", BTC_ETH)
        val list = CurrenciesDataModelResponses::class.members // the members are getETC_BTC() and so on
                .filter { it.call(item) != null }
                .map { TickerModelResponse(it.name, it.call(item) as TickerDataModelResponse) }
        return TickerModelResponses(list)

    }
//    val id: Int, val name: String, val lowestAsk: String, val highestBid: String,
//    val percentChange: String, val baseVolume: String, val quoteVolume: String,
//    val high24hr: String, val low24hr: String
//    val mapper3 = fun(it: TickerDataModelResponses) = Observable.fromArray(TickerModelResponse("BTC_ETH", it.BTC_ETH.last))
//
//
//    fun mapper2(it: TickerDataModelResponses): Observable<TickerModelResponse> {
//        return Observable.fromArray(TickerModelResponse("BTC_ETH", it.BTC_ETH.last), TickerModelResponse("BTC_LTC", it.BTC_LTC.last))
//
//    }
//
//    fun mapper4(): Function<TickerDataModelResponses, Observable<TickerModelResponse>> {
//        return Function<TickerDataModelResponses, Observable<TickerModelResponse>> {
//            Observable.fromArray(TickerModelResponse("BTC_ETH", it.BTC_ETH.last), TickerModelResponse("BTC_LTC", it.BTC_LTC.last))
//        }
//    }


    @Test
    fun test1() {
        val testObserver = TestObserver<TickerModelResponses>()
        val a = Observable.fromCallable { getCurrenciesList() }
//                .subscribeOn(testScheduler)
//                .observeOn(testScheduler)
                .map { mapperModelResponses(it) }
                .doOnNext {
                    print(it.toString())
//                    testSubscriber.onNext(it)
                }
                .doOnError {
                    print(it.message)
                }
                .doOnComplete {
                    print("Completed")
                }
                .subscribe(testObserver)

        val values = testObserver.values()
        testObserver.assertNoErrors()
    }


    fun getCurrenciesList(): CurrenciesDataModelResponses {
        val item1 = CurrenciesDataModelResponse("BTC", "Bitcoin")
        val item2 = CurrenciesDataModelResponse("ETH", "Ethereum");
        val item3 = CurrenciesDataModelResponse("LTC", "Litecoin");
        var list = CurrenciesDataModelResponses(item1, item2, item3)
        return list
    }

    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(CurrencyJsonAdapter())
            .add(CurrencyMapJsonAdapter())
            .build()
    }

    fun s() : String = "{\"1CR\":{\"id\":1,\"name\":\"1CRedit\",\"txFee\":\"0.01000000\",\"minConf\":3,\"depositAddress\":null,\"disabled\":1,\"delisted\":0,\"frozen\":0},\"ABY\":{\"id\":2,\"name\":\"ArtByte\",\"txFee\":\"0.01000000\",\"minConf\":8,\"depositAddress\":null,\"disabled\":0,\"delisted\":1,\"frozen\":0}}"


    @Test
    fun my(): Unit {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val moshi = moshi()
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val tk = retrofit.create(TickerService::class.java);
        val input = s()

        val call = tk.getCurrencies()
        val m = call.blockingFirst().currenciesMap


        val jsonAdapter = moshi.adapter(CurrenciesMap::class.java)
        val item = jsonAdapter.fromJson(input)
        item.currenciesMap
    }
}