package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.project.Profile
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
object TaskProfileDownloadFiles : Task {

    override fun run(profile: Profile) {
        ink.pmc.serverpacker.logger.info("Downloading files for ${profile.name}...")

        val softwareDownloadJob = GlobalScope.launch {
            val result = profile.software.resolve()

            if (!result) {
                ink.pmc.serverpacker.logger.warn("Can't get ${profile.software.url}!")
            }
        }

        val pluginDownloadJob = GlobalScope.launch {
            profile.plugins.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        ink.pmc.serverpacker.logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }

        val modsDownloadJob = GlobalScope.launch {
            profile.mods.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        ink.pmc.serverpacker.logger.warn("Can't get ${it.url}!")
                    }
                }
            }
        }

        val configsDownloadJob = GlobalScope.launch {
            profile.configs.contents.forEach {
                launch {
                    val result = it.resolve()

                    if (!result) {
                        ink.pmc.serverpacker.logger.warn("Can't get ${it.url}!")
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