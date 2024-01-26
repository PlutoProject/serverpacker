package link.plutomc.serverpacker.utils

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader

object NetworkUtils {

    val HTTP_CLIENT = OkHttpClient()
    const val USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"

    fun requestAndGetRspJsonString(url: String): String {
        val req = Request.Builder()
            .url(url)
            .header("User-Agent", USER_AGENT)
            .build()

        val rsp = HTTP_CLIENT.newCall(req).execute()

        if (!rsp.isSuccessful) {
            return ""
        }

        val inputStream = rsp.body.byteStream()
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.use { it.readText() }
    }

    fun requestAndGetRspJsonObject(url: String): Pair<Boolean, JsonObject?> {
        val jsonString = requestAndGetRspJsonString(url)
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        return Pair(true, jsonObject)
    }

    fun requestAndGetRspJsonElement(url: String): Pair<Boolean, JsonElement?> {
        val jsonString = requestAndGetRspJsonString(url)
        val jsonElement = JsonParser.parseString(jsonString)
        return Pair(true, jsonElement)
    }

}