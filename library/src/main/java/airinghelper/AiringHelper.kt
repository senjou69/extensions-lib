package airinghelper

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import kotlinx.serialization.json.Json
import eu.kanade.tachiyomi.network.POST
import kotlinx.serialization.json.jsonObject
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.time.Instant.ofEpochSecond
import java.time.format.DateTimeFormatter.ISO_INSTANT
import java.util.TimeZone


data class Nextepwen(
    val animeid: String,
    val episode: String,
    val airingat: String,
    val timeuntilairing: String
)

fun whenNextep(title: String): Nextepwen {
    val apiUrl = "https://graphql.anilist.co"
    val query =
        "{\"query\":\"query(\$query: String){Media(type:ANIME,search:\$query){id nextAiringEpisode{episode airingAt timeUntilAiring}}}\",\"variables\":{\"query\":\"$title\"}}"
    var (animeid, airingat, episode, timeuntilairing) = listOf("0", "0", "0", "0")

    val client = OkHttpClient()
    val headers = Headers.Builder().apply {
        add("Content-Type", "application/json")
        add("Accept", "application/json")
    }.build()
    val body = query.toRequestBody("application/json".toMediaType())
    val coverResponse = client.newCall(POST(apiUrl, headers, body)).execute().body!!.string()
    Log.d("airingjson", coverResponse)

    val response = Json.parseToJsonElement(coverResponse).jsonObject["data"]!!.jsonObject["Media"]

    return if (response!!.jsonObject["nextAiringEpisode"].toString() != "null") {
        animeid = response.jsonObject["id"].toString()
        airingat = response.jsonObject["nextAiringEpisode"]!!.jsonObject["airingAt"].toString()
        episode = response.jsonObject["nextAiringEpisode"]!!.jsonObject["episode"].toString()
        timeuntilairing =
            response.jsonObject["nextAiringEpisode"]!!.jsonObject["timeUntilAiring"].toString()
        Nextepwen(animeid, episode, airingat, timeuntilairing)
    } else {
        Nextepwen(animeid, episode, airingat, timeuntilairing)
    }
}


@SuppressLint("SimpleDateFormat")
fun parseEpoch(time: String): String? {
    val unix_seconds = time.toLong()
    val timestampAsDateString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        ISO_INSTANT.format(ofEpochSecond(unix_seconds))
    } else {
        //TODO("VERSION.SDK_INT < O")
        return null
    }
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    parser.timeZone = TimeZone.getTimeZone("UTC")
    val formatter = SimpleDateFormat("MMM dd,yyyy HH:mm")
    return formatter.format(parser.parse(timestampAsDateString))
}

fun airingmsg(episode: String, airingat: String): String {
    return if (airingat == "0") {
        ""
    } else {
        """EP$episode ~ ${parseEpoch(airingat)}"""
    }

}

class AiringHelper{
    val a = "b"
}