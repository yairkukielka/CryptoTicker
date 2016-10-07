package com.yairkukielka.ticker.executor

interface MainThread {
    fun post(runnable: Runnable)
}
