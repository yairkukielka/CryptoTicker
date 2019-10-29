package com.yairkukielka.ticker.executor

import android.os.Handler
import android.os.Looper

class MainThreadImpl : MainThread {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }
}