package com.yairkukielka.ticker.net

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class NetworkManagerImpl(private val client: OkHttpClient) : NetworkManager {

    @Throws(IOException::class)
    override fun makeCall(): String {
        val url = "https://github.com/square/okhttp" // test url
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }
}
