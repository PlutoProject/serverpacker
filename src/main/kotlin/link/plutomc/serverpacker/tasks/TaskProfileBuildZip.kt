package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.outputsDir
import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.utils.CompressUtils
import java.io.File

object TaskProfileBuildZip: Task {

    override fun run(profile: Profile) {
        logger.info("Building zip archive for ${profile.name}...")
        CompressUtils.compressFolderContentsToZip(profile.profileDir, File(outputsDir, "${profile.name}-${profile.version}.zip"))
    }

}