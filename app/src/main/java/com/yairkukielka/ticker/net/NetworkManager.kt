package com.yairkukielka.ticker.net

import java.io.IOException

interface NetworkManager {
    @Throws(IOException::class)
    fun makeCall(): String
}
