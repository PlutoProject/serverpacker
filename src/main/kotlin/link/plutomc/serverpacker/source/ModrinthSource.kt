package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.downloadCacheDir
import link.plutomc.serverpacker.utils.DownloadUtils
import link.plutomc.serverpacker.utils.ModrinthUtils
import java.io.File

class ModrinthSource(
    versionId: String
) : Source {

    constructor(projectId: String, gameVersion: String) : this(ModrinthUtils.getMatchedVersion(projectId, gameVersion))

    val downloadUrl: String
    val fileName: String

    init {
        if (versionId != "") {
            val pair = ModrinthUtils.getVersionDownloadUrlAndFilename(versionId)
            downloadUrl = pair.second
            fileName = pair.first
        } else {
            downloadUrl = ""
            fileName = ""
        }
    }

    override val file: File
        get() = File(downloadCacheDir, fileName)
    override val url: String
        get() = downloadUrl

    override fun resolve(): Boolean {
        return DownloadUtils.download(downloadUrl, file)
    }

}