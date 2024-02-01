package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.utils.fileCheckOrCreate
import org.apache.commons.io.FileUtils
import java.io.File

object TaskProfileGenerateEulaFile : Task {

    override fun run(profile: Profile) {
        logger.info("Generating Eula file for ${profile.name}...")

        val file = File(profile.profileDir, "eula.txt").fileCheckOrCreate()
        val content = "eula=${profile.eula}"

        if (!profile.eula) {
            logger.warn("You haven't agreed the Mojang Eula. The server won't start properly.")
        }

        FileUtils.writeStringToFile(file, content, "UTF-8")
    }

}