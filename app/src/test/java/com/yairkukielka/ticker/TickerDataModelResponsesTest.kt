package com.yairkukielka.ticker

import android.util.Log
import com.yairkukielka.ticker.data.TickerDataModelResponse
import com.yairkukielka.ticker.data.TickerDataModelResponses
import com.yairkukielka.ticker.data.TickerModelResponse
import com.yairkukielka.ticker.data.TickerModelResponses
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
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Yair Kukielka on 9/6/16.
 */
class TickerDataModelResponsesTest() {

    //    val mapper: (TickerDataModelResponses) -> Observable<TickerModelResponse> = {
//        Observable.fromArray(
//                TickerModelResponse("BTC_ETH", it.BTC_ETH),
//                TickerModelResponse("BTC_LTC", it.BTC_LTC))
//    }

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






//    val mapperTickerModelResponses: (TickerDataModelResponses) -> TickerModelResponses = { mapToTickerModelResponse(it) }
//
//    private fun mapToTickerModelResponse(item: TickerDataModelResponses): TickerModelResponses {
//        // this gets the name and value of TickerDataModelResponses.BTC_ETH and creates a TickerModelResponse("BTC_ETH", BTC_ETH)
//        val list = TickerDataModelResponses::class.members // the members are getETC_BTC() and so on
//                .filter { it.name.contains('_') }
//                .map { TickerModelResponse(it.name, it.name, it.call(item) as TickerDataModelResponse) }
//        return TickerModelResponses(list)
//
//    }
//
//    @Test
//    fun test1() {
//        val testObserver = TestObserver<TickerModelResponses>()
//        val a = Observable.fromCallable { getTickerList() }
//                .map { mapperTickerModelResponses(it) }
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
//
//
//    fun getTickerList(): TickerDataModelResponses {
//        val item1 = TickerDataModelResponse(0, "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019");
//        val item2 = TickerDataModelResponse(1, "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0");
//        val item3 = TickerDataModelResponse(1, "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0");
//        val list = TickerDataModelResponses(item1, item2)
//        return list
//    }


}