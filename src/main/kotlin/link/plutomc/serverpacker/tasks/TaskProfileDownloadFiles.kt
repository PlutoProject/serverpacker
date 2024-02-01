package link.plutomc.serverpacker.tasks

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Profile

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

        val pluginDownloadJob = GlobalScope.launch {
            profile.plugins.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }

        val modsDownloadJob = GlobalScope.launch {
            profile.mods.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }

        val configsDownloadJob = GlobalScope.launch {
            profile.configs.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }

        runBlocking {
            softwareDownloadJob.join()
            pluginDownloadJob.join()
            modsDownloadJob.join()
            configsDownloadJob.join()
        }
    }

}