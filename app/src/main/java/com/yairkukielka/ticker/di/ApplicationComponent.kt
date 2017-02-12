package com.yairkukielka.ticker.di

import com.yairkukielka.ticker.TickerApplication
import com.yairkukielka.ticker.domain.HomePresenter
import com.yairkukielka.ticker.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun injectApplication(application: TickerApplication)

    fun injectActivity(activity: HomeActivity)

    fun homePresenter(): HomePresenter
}
