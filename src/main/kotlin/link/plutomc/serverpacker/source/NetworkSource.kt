package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.downloadCacheDir
import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Remote
import link.plutomc.serverpacker.utils.DownloadUtils
import java.io.File

class NetworkSource(private val url: String, private val customFileName: String = "") : Source {

    override val file: File
        get() {
            if (customFileName != "") {
                return File(downloadCacheDir, customFileName)
            }

            return File(downloadCacheDir, checkNotNull(DownloadUtils.getFilenameWithoutDownload(url)))
        }

    override fun resolve(): Boolean {
        return DownloadUtils.download(url, file)
    }

}