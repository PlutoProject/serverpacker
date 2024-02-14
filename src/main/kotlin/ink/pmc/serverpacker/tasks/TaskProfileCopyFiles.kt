package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.project.Profile
import ink.pmc.serverpacker.utils.copyFileOrDirectory
import java.io.File

object TaskProfileCopyFiles : Task {

    override fun run(profile: Profile) {
        ink.pmc.serverpacker.logger.info("Copying cache files for ${profile.name}...")

        copyFileOrDirectory(profile.software.file, File(profile.profileDir, profile.software.file.name))

        profile.plugins.copyContents()
        profile.mods.copyContents()
        profile.configs.copyContents()
        profile.root.copyContents()

        copyFileOrDirectory(
            File(ink.pmc.serverpacker.startScriptCache, "start_windows.bat"),
            File(profile.profileDir, "start_windows.bat")
        )
        copyFileOrDirectory(
            File(ink.pmc.serverpacker.startScriptCache, "start_linux_unix.sh"),
            File(profile.profileDir, "start_linux_unix.sh")
        )
    }

}