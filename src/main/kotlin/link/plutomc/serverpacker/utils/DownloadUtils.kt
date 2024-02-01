package link.plutomc.serverpacker.utils

import link.plutomc.serverpacker.downloadCacheDir
import link.plutomc.serverpacker.logger
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream

object DownloadUtils {


    private const val BUFFER_SIZE = 4096


    fun download(source: String, destFile: File): Boolean {
        val req = Request.Builder()
            .url(source)
            .header("User-Agent", NetworkUtils.USER_AGENT)
            .build()

        val rsp = NetworkUtils.HTTP_CLIENT.newCall(req).execute()

        if (!rsp.isSuccessful) {
            logger.error("Failed to download: $source, code: ${rsp.code}, message: ${rsp.message}!")
            return false
        }

        if (getFilenameWithoutDownload(source) == null) {
            return false
        }

/*        val fileName: String = if (customFileName == "") {
            checkNotNull(getFilenameWithoutDownload(source))
        } else {
            customFileName
        }*/

        if (destFile.exists()) {
            logger.info("${destFile.name} already existed in cache! Skipped download.")
            return true
        }

        val inputStream = rsp.body.byteStream()
        val outputStream = FileOutputStream(destFile)
        val buffer = ByteArray(BUFFER_SIZE)
        var bytesRead: Int

        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }

        logger.debug("Downloaded: $source")
        return true
    }

    fun getFilenameWithoutDownload(source: String): String? {
        val req = Request.Builder()
            .url(source)
            .head()
            .header("User-Agent", NetworkUtils.USER_AGENT)
            .build()

        val rsp = NetworkUtils.HTTP_CLIENT.newCall(req).execute()

        if (!rsp.isSuccessful) {
            return null
        }

        return getFileNameFromHeaders(rsp) ?: source.substring(source.lastIndexOf('/'), source.length)
    }

    private fun getFileNameFromHeaders(response: Response): String? {
        val contentDisposition = response.header("Content-Disposition")

        if (!contentDisposition.isNullOrEmpty()) {
            val parts = contentDisposition.split(";")
            for (part in parts) {
                if (part.trim().startsWith("filename")) {
                    val fileNameParts = part.split("=")
                    if (fileNameParts.size > 1) {
                        return fileNameParts[1].trim().replace("\"", "")
                    }
                }
            }
        }

        return null
    }

}