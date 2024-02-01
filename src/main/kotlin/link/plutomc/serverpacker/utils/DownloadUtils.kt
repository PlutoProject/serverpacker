package link.plutomc.serverpacker.utils

import link.plutomc.serverpacker.downloadCacheDir
import link.plutomc.serverpacker.logger
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.net.URLDecoder

object DownloadUtils {


    const val BUFFER_SIZE = 4096


    fun download(source: String, destFile: File): Boolean {
        if (destFile.exists()) {
            logger.info("${destFile.name} already existed in cache! Skipped download.")
            return true
        }

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

        val inputStream = rsp.body.byteStream()
        val outputStream = FileOutputStream(destFile)
        val buffer = ByteArray(BUFFER_SIZE)
        var bytesRead: Int

        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }

        logger.info("Downloaded: $source")
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
            extractFileName(contentDisposition)
        }

        return null
    }

    fun extractFileName(contentDisposition: String): String? {
        // 尝试匹配基本形式
        val basicRegex = """filename="([^"]*)"""".toRegex(RegexOption.IGNORE_CASE)
        basicRegex.find(contentDisposition)?.let {
            return it.groupValues[1]
        }

        // 尝试匹配编码扩展形式，同时提取字符集
        val extendedRegex = """filename\*=([^']*)'[^']*'([^']+)(?:;|$)""".toRegex(RegexOption.IGNORE_CASE)
        extendedRegex.find(contentDisposition)?.let {
            val charset = it.groupValues[1].ifEmpty { "UTF-8" } // 如果没有指定字符集，默认使用 UTF-8
            val encodedFilename = it.groupValues[2]
            return URLDecoder.decode(encodedFilename, charset)
        }

        return null
    }

}