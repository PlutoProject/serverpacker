package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Profile

object TaskProfileGenerateScript : Task {

    override fun run(profile: Profile) {
        logger.info("Generating start script for ${profile.name}...")

        profile.startScript.apply {
            jar = profile.software.file.name
        }

        profile.startScript.generateWindows()
        profile.startScript.generateUnixOrLinux()
    }

}