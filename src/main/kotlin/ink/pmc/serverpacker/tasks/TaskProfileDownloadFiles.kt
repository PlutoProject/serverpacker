package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.logger
import ink.pmc.serverpacker.project.Profile
import ink.pmc.serverpacker.project.folder.Folder
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
object TaskProfileDownloadFiles : Task {

    override fun run(profile: Profile) {
        logger.info("Downloading files for ${profile.name}...")

        val softwareDownloadJob = GlobalScope.launch {
            val result = profile.software.resolve()

            if (!result) {
                logger.warn("Can't get ${profile.software.url}!")
            }
        }

        val pluginDownloadJob = download(profile.plugins)
        val modsDownloadJob = download(profile.mods)
        val configsDownloadJob = download(profile.configs)

        runBlocking {
            softwareDownloadJob.join()
            pluginDownloadJob.join()
            modsDownloadJob.join()
            configsDownloadJob.join()
        }
    }

    private fun download(folder: Folder): Job {
        return GlobalScope.launch {
            folder.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }
    }

}