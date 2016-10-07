package com.yairkukielka.ticker.di

import com.yairkukielka.ticker.TickerApplication
import com.yairkukielka.ticker.domain.HomePresenter
import com.yairkukielka.ticker.ui.HomeActivity
import com.yairkukielka.ticker.ui.MyFragment
import dagger.Component
import okhttp3.OkHttpClient
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun injectApplication(application: TickerApplication)

    fun injectActivity(activity: HomeActivity)
//    fun injectFragment(fragment: MyFragment)
//    fun okHttpClient(): OkHttpClient

    fun homePresenter(): HomePresenter
}
