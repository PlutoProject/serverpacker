package ink.pmc.serverpacker.utils

object SpigotUtils {

    private const val SPIGOT_API = "https://api.spiget.org/v2"

    fun getDownloadId(resId: String, versionId: String = ""): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("$SPIGOT_API/resources/$resId")

        if (versionId != "") {
            stringBuilder.append("/versions/$versionId/download")
            return stringBuilder.toString()
        }

        stringBuilder.append("/download")
        return stringBuilder.toString()
    }

    fun getResourceName(resId: String): String {
        val url = "$SPIGOT_API/resources/$resId"
        val jsonObject = NetworkUtils.requestAndGetRspJsonObject(url)

        if (!jsonObject.first) {
            return ""
        }

        val fileType = jsonObject.second!!.get("file").asJsonObject.get("type").asString

        return jsonObject.second!!.get("name").asString + fileType
    }

}