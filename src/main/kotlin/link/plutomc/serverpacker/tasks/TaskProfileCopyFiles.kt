package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.startScriptCache
import link.plutomc.serverpacker.utils.copyFileOrDirectory
import java.io.File

object TaskProfileCopyFiles : Task {

    override fun run(profile: Profile) {
        logger.info("Copying cache files for ${profile.name}...")

        copyFileOrDirectory(profile.software.file, File(profile.profileDir, profile.software.file.name))

        profile.plugins.copyContents()
        profile.mods.copyContents()
        profile.configs.copyContents()
        profile.root.copyContents()

        copyFileOrDirectory(File(startScriptCache, "start_windows.bat"), File(profile.profileDir, "start_windows.bat"))
        copyFileOrDirectory(
            File(startScriptCache, "start_linux_unix.sh"),
            File(profile.profileDir, "start_linux_unix.sh")
        )
    }

}