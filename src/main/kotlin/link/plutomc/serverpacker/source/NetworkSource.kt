package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.project.Remote
import link.plutomc.serverpacker.downloadCacheDir
import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.utils.DownloadUtils
import java.io.File

open class NetworkSource(private val url: String, private val customFileName: String = "") : Source, Remote {

    override val file: File?
        get() {
            val downloadStatus = download()

            if (!downloadStatus.first) {
                return null
            }

            return File(downloadCacheDir, downloadStatus.second)
        }
    override val reachable: Boolean
        get() = true

    override fun download(): Pair<Boolean, String> {
        logger.info("Downloading a network source:  $url...")

        if (customFileName != "") {
            return DownloadUtils.download(url, downloadCacheDir, customFileName)
        }

        return DownloadUtils.download(url, downloadCacheDir)
    }

}