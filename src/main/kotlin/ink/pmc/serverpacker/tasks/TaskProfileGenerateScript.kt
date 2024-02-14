package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.project.Profile

object TaskProfileGenerateScript : Task {

    override fun run(profile: Profile) {
        ink.pmc.serverpacker.logger.info("Generating start script for ${profile.name}...")

        profile.startScript.apply {
            jar = profile.software.file.name
        }

        profile.startScript.generateWindows()
        profile.startScript.generateUnixOrLinux()
    }

}