package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.logger
import ink.pmc.serverpacker.project.Profile
import ink.pmc.serverpacker.utils.CompressUtils
import java.io.File

object TaskProfileBuildZip : Task {

    override fun run(profile: Profile) {
        logger.info("Building zip archive for ${profile.name}...")
        CompressUtils.compressFolderContentsToZip(
            profile.profileDir,
            File(ink.pmc.serverpacker.outputsDir, "${profile.name}-${profile.version}.zip")
        )
    }

}