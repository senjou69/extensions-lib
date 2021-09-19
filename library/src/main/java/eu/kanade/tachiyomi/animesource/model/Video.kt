package eu.kanade.tachiyomi.animesource.model

import android.net.Uri
import okhttp3.Headers

data class Video(val url: String, val quality: String, var videoUrl: String?, var uri: Uri?, val headers: Headers? = null)
