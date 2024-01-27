package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.project.Remote
import link.plutomc.serverpacker.downloadCacheDir
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
    private val _file: File?
    private var _reachable = false

    init {
        if (versionId != "") {
            val pair = ModrinthUtils.getVersionDownloadUrlAndFilename(versionId)
            downloadUrl = pair.second
            fileName = pair.first
            _file = File(downloadCacheDir, fileName)
            _reachable = true
        } else {
            downloadUrl = ""
            fileName = ""
            _file = null
            _reachable = false
        }
    }

    override fun download(): Pair<Boolean, String> {
        /*
        if (_file.exists()) {
            logger.info("$fileName already existed in cache! Skipped download.")
            return Pair(true, fileName)
        }
        */
        logger.info("Downloading $fileName...")
        return DownloadUtils.download(downloadUrl, downloadCacheDir, fileName)
    }

    override val file: File?
        get() {
            var downloadResult = false

            if (!_file!!.exists()) {
                downloadResult = download().first
            }

            if (!downloadResult) {
                return null
            }

            return _file
        }
    override val reachable: Boolean
        get() = _reachable

}