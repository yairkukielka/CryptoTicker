package com.yairkukielka.ticker.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.yairkukielka.ticker.adapter.CurrencyMapJsonAdapter
import com.yairkukielka.ticker.adapter.TickerDataModelResponsesAdapter
import com.yairkukielka.ticker.api.Api
import com.yairkukielka.ticker.api.TickerService
import com.yairkukielka.ticker.domain.HomePresenter
import com.yairkukielka.ticker.executor.Executor
import com.yairkukielka.ticker.executor.MainThread
import com.yairkukielka.ticker.executor.MainThreadImpl
import com.yairkukielka.ticker.executor.ThreadExecutor
import com.yairkukielka.ticker.net.NetworkManager
import com.yairkukielka.ticker.net.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun application(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun httpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    internal fun provideTickerService(retrofit: Retrofit): TickerService {
        return retrofit.create(TickerService::class.java)
    }

    @Provides
    @Singleton
    internal fun networkManager(client: OkHttpClient): NetworkManager {
        return NetworkManagerImpl(client)
    }

    @Provides
    @Singleton
    internal fun provideExecutor(): Executor {
        return ThreadExecutor()
    }

    @Provides
    @Singleton
    internal fun provideApi(tickerService: TickerService): Api {
        return Api(tickerService)
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(TickerDataModelResponsesAdapter())
                .add(CurrencyMapJsonAdapter())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    @Provides
    @Singleton
    internal fun provideMainThread(): MainThread {
        return MainThreadImpl()
    }

    @Provides
    @Singleton
    internal fun provideHomePresenter(api: Api): HomePresenter {
        return HomePresenter(api)
    }
}

