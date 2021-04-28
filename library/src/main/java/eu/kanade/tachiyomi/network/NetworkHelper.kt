package eu.kanade.tachiyomi.network

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class NetworkHelper(context: Context) {

    val client by lazy {
        val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

        builder.build()
    }

    val cloudflareClient: OkHttpClient = throw Exception("Stub!")
}