package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.startScriptCache
import org.apache.commons.io.FileUtils
import java.io.File

object TaskProfileCopyFiles: Task {

    override fun run(profile: Profile) {
        logger.info("Copying cache files for ${profile.name}...")

        FileUtils.copyFile(profile.software.file, File(profile.profileDir, profile.software.file.name))

        profile.plugins.copyContents()
        profile.mods.copyContents()
        profile.configs.copyContents()

        FileUtils.copyFile(File(startScriptCache, "start_windows.bat"), File(profile.profileDir, "start_windows.bat"))
        FileUtils.copyFile(File(startScriptCache, "start_linux_unix.sh"), File(profile.profileDir, "start_linux_unix.sh"))
    }

}