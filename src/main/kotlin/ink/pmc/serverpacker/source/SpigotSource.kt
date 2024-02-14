package ink.pmc.serverpacker.source

import ink.pmc.serverpacker.utils.SpigotUtils

class SpigotSource(resId: String, versionId: String = "") : Source,
    NetworkSource(SpigotUtils.getDownloadId(resId, versionId), SpigotUtils.getResourceName(resId))