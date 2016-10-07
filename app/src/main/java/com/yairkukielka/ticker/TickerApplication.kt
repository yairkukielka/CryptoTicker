package com.yairkukielka.ticker

import android.app.Application

import com.yairkukielka.ticker.di.ApplicationComponent
import com.yairkukielka.ticker.di.ApplicationModule
import com.yairkukielka.ticker.di.DaggerApplicationComponent

class TickerApplication : Application() {

    companion object {
        lateinit var application: TickerApplication
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.injectApplication(this)
    }

}
