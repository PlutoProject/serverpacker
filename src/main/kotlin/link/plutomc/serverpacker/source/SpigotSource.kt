package link.plutomc.serverpacker.source

import link.plutomc.serverpacker.project.Remote
import link.plutomc.serverpacker.utils.SpigotUtils

class SpigotSource(
    resId: String,
    versionId: String = ""
) : Source, Remote, NetworkSource(SpigotUtils.getDownloadId(resId, versionId), SpigotUtils.getResourceName(resId))