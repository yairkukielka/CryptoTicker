package com.yairkukielka.ticker

import com.squareup.moshi.Moshi
import com.yairkukielka.ticker.adapter.CurrencyMapJsonAdapter
import com.yairkukielka.ticker.data.*
import com.yairkukielka.ticker.domain.CurrencyInfo
import com.yairkukielka.ticker.domain.HomePresenter
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.BehaviorSubject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Created by Yair Kukielka on 9/6/16.
 */
class CurrenciesDataModelResponsesTest() {

//    val mapperModelResponses: (CurrenciesDataModelResponses) -> TickerModelResponses = { mapToModelResponse(it) }
//
//    private fun mapToModelResponse(item: CurrenciesDataModelResponses): TickerModelResponses {
//        // this gets the name and value of TickerDataModelResponses.BTC_ETH and creates a TickerModelResponse("BTC_ETH", BTC_ETH)
//        val list = CurrenciesDataModelResponses::class.members // the members are getETC_BTC() and so on
//                .filter { it.call(currency_item) != null }
//                .map { TickerModelResponse(it.name, it.name, it.call(currency_item) as TickerDataModelResponse) }
//        return TickerModelResponses(list)
//
//    }


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
    fun tickerDataModelResponses() {
        // disposableObserver
        val testObserver = TestObserver<TickerModelResponses>()
        val a = getObservable()
        a.blockingSubscribe(testObserver)

        val values = testObserver.values()
        testObserver.assertNoErrors()
    }

    fun getObservable(): Observable<TickerModelResponses> {
        return Observable.interval(0L, 4L, TimeUnit.SECONDS)
                .take(2)
                .concatMap { it -> Observable.fromCallable { getTickerDataModelResponses() } }
                .withLatestFrom(getCurrencies(), HomePresenter.composer)
                .doOnNext {
                    print(it.toString())
                }
                .doOnError {
                    print(it.message)
                }
                .doOnComplete {
                    print(" Completed")
                }
    }

    fun getCurrencies(): BehaviorSubject<CurrenciesMap> {
        val map: CurrenciesMap = CurrenciesMap(mapOf(Pair("BTC", CurrencyInfo("BTC", "Bitcoin")), Pair("ETH", CurrencyInfo("ETH", "Ethereum"))))
        return BehaviorSubject.createDefault(map)
    }

    fun getTickerDataModelResponses(): TickerDataModelResponses {
        val item1 = TickerDataModelResponse("BTC_ETH", "0.1")
        val item2 = TickerDataModelResponse("ETH_BTC", "0.2");
//        var list: MutableList<TickerDataModelResponse> = mutableListOf(item1, item2)
        return TickerDataModelResponses(listOf(item1, item2))
    }


//    @Test
//    fun testTickerModelResponse() {
//        val testObserver = TestObserver<TickerModelResponses>()
//        val a = Observable.fromCallable { getCurrenciesList() }
//                .map { mapperModelResponses(it) }
//                .doOnNext {
//                    print(it.toString())
//                }
//                .doOnError {
//                    print(it.message)
//                }
//                .doOnComplete {
//                    print("Completed")
//                }
//                .subscribe(testObserver)
//
//        val values = testObserver.values()
//        testObserver.assertNoErrors()
//    }


    fun getCurrenciesList(): CurrenciesDataModelResponses {
        val item1 = CurrenciesDataModelResponse("BTC", "Bitcoin")
        val item2 = CurrenciesDataModelResponse("ETH", "Ethereum");
        val item3 = CurrenciesDataModelResponse("LTC", "Litecoin");
        var list = CurrenciesDataModelResponses(item1, item2, item3)
        return list
    }

    fun moshi(): Moshi {
        return Moshi.Builder()
                .add(CurrencyMapJsonAdapter())
                .build()
    }

    //    fun s() : String = "{\"1CR\":{\"id\":1,\"name\":\"1CRedit\",\"txFee\":\"0.01000000\",\"minConf\":3,\"depositAddress\":null,\"disabled\":1,\"delisted\":0,\"frozen\":0},\"ABY\":{\"id\":2,\"name\":\"ArtByte\",\"txFee\":\"0.01000000\",\"minConf\":8,\"depositAddress\":null,\"disabled\":0,\"delisted\":1,\"frozen\":0}}"
    fun s(): String = "{\"1CR\":{\"name\":\"1CRedit\"}, \"2CR\":{\"name\":\"2CRedit\"}}"

    @Test
    fun my(): Unit {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val moshi = moshi()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(Api.BASE_URL)
//            .client(client)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//        val tk = retrofit.create(TickerService::class.java)
        val input = s()

//        val call = tk.getCurrencies(Api.RETURN_CURRENCIES_QUERY)
//        val m = call.blockingFirst()

        val jsonAdapter = moshi.adapter(CurrenciesMap::class.java)
        val m = jsonAdapter.fromJson(input)
        m.map

    }
}