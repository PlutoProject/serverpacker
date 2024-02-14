package ink.pmc.serverpacker.source

import ink.pmc.serverpacker.utils.DownloadUtils
import java.io.File

open class NetworkSource(override val url: String, private val customFileName: String = "") : Source {

    override val file: File
        get() {
            if (customFileName != "") {
                return File(ink.pmc.serverpacker.downloadCacheDir, customFileName)
            }

            return File(
                ink.pmc.serverpacker.downloadCacheDir,
                checkNotNull(DownloadUtils.getFilenameWithoutDownload(url))
            )
        }

    override fun resolve(): Boolean {
        return DownloadUtils.download(url, file)
    }

}