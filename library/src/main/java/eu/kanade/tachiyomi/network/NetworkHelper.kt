package eu.kanade.tachiyomi.network

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class NetworkHelper(context: Context) {

    val client: OkHttpClient = throw Exception("Stub!")

    val cloudflareClient: OkHttpClient = throw Exception("Stub!")

    val cookieManager: AndroidCookieJar = throw Exception("Stub!")
}

class AndroidCookieJar : CookieJar {

    private val manager: CookieManager = throw Exception("Stub!")

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>): Unit = throw Exception("Stub!")

    override fun loadForRequest(url: HttpUrl): List<Cookie> = throw Exception("Stub!")

    fun get(url: HttpUrl): List<Cookie> = throw Exception("Stub!")

    fun remove(url: HttpUrl, cookieNames: List<String>? = null, maxAge: Int = -1): Unit = throw Exception("Stub!")

    fun removeAll(): Unit = throw Exception("Stub!")
}