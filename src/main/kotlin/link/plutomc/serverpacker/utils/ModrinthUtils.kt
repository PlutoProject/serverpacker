package link.plutomc.serverpacker.utils

import com.google.gson.JsonElement
import link.plutomc.serverpacker.logger

@Suppress("UNUSED")
object ModrinthUtils {

    private const val MODRINTH_API = "https://api.modrinth.com/v2"

    private fun getVersionFileList(versionId: String): List<JsonElement> {
        val url = "$MODRINTH_API/version/$versionId"
        val reqPair = NetworkUtils.requestAndGetRspJsonObject(url)

        if (!reqPair.first) {
            logger.error("Failed to get a Modrinth version: $url")
        }

        val jsonObject = checkNotNull(reqPair.second)

        return jsonObject.get("files").asJsonArray.asList()
    }

    fun getVersionFileName(versionId: String): String {
        return getVersionFileList(versionId)[0].asJsonObject.get("filename").asString
    }

    fun getVersionDownloadUrl(versionId: String): String {
        return getVersionFileList(versionId)[0].asJsonObject.get("url").asString
    }

    fun getVersionDownloadUrlAndFilename(versionId: String): Pair<String, String> {
        val list = getVersionFileList(versionId)
        val name = list[0].asJsonObject.get("filename").asString
        val url = list[0].asJsonObject.get("url").asString
        return Pair(name, url)
    }

    fun getProjectVersions(projectId: String): List<JsonElement> {
        val url = "$MODRINTH_API/project/$projectId/version"
        val reqPair = NetworkUtils.requestAndGetRspJsonElement(url)

        if (!reqPair.first) {
            logger.error("Failed to get a Modrinth project: $url")
        }

        val jsonElement = checkNotNull(reqPair.second)

        return jsonElement.asJsonArray.asList()
    }

    fun getMatchedVersion(projectId: String, gameVersion: String): String {
        val versions = getProjectVersions(projectId)

        versions.forEach { version ->
            val jsonObject = version.asJsonObject
            val compatibleVersions = jsonObject.get("game_versions").asJsonArray
            if (compatibleVersions.any { it.asString == gameVersion }) {
                return jsonObject.get("id").asString
            }
        }

        return ""
    }

}