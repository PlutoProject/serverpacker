package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.Remote
import link.plutomc.serverpacker.downloadCacheLocation
import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.utils.DownloadUtils
import link.plutomc.serverpacker.utils.ModrinthUtils
import java.io.File

class ModrinthSource(
    versionId: String
) : Source, Remote {

    constructor(projectId: String, gameVersion: String) : this(ModrinthUtils.getMatchedVersion(projectId, gameVersion))

    val downloadUrl: String
    val fileName: String
    private val _file: File

    init {
        val pair = ModrinthUtils.getVersionDownloadUrlAndFilename(versionId)
        downloadUrl = pair.second
        fileName = pair.first
        _file = File(downloadCacheLocation, fileName)
    }

    override fun download(): Pair<Boolean, String> {
        if (_file.exists()) {
            logger.info("$fileName already existed in cache! Skipped download.")
            return Pair(true, fileName)
        }

        logger.info("Downloading $fileName...")
        return DownloadUtils.download(downloadUrl, downloadCacheLocation, fileName)
    }

    override val file: File?
        get() {
            var downloadResult = false

            if (!_file.exists()) {
                downloadResult = download().first
            }

            if (!downloadResult) {
                return null
            }

            return _file
        }

}