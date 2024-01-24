package link.plutomc.serverpacker.impl

import link.plutomc.serverpacker.api.Configuration
import link.plutomc.serverpacker.api.Profile
import link.plutomc.serverpacker.api.Project
import link.plutomc.serverpacker.api.file.LocalFile
import link.plutomc.serverpacker.api.plugin.Plugin

class ServerProfile(
    private val project: Project,
    override val name: String,
    override val software: LocalFile
) : Profile {

    private val _plugins: MutableList<Plugin> = mutableListOf()
    private val _configurations: MutableList<Configuration> = mutableListOf()
    private val _files: MutableList<LocalFile> = mutableListOf()
    override val executionCommand: String
        get() = TODO("Not yet implemented")

    override val plugins: Collection<Plugin>
        get() = _plugins
    override val configurations: Collection<Configuration>
        get() = _configurations
    override val files: Collection<LocalFile>
        get() = _files

    override fun addPlugin(plugin: Plugin) {
        _plugins.add(plugin)
    }

    override fun addConfiguration(config: Configuration) {
        _configurations.add(config)
    }

    override fun addFile(file: LocalFile) {
        _files.add(file)
    }

    override fun buildZip() {
        /*        val cacheDir = File(project.workDir, "caches${File.separator}buildZip")
                cacheDir.mkdirs()

                software.actualFile.copyTo(File(cacheDir, "${software.actualFile.name}.jar"))

                val pluginsDir = File(cacheDir, "plugins")
                pluginsDir.mkdirs()

                plugins.forEach { it.actualFile.copyTo(File(pluginsDir, "${it.actualFile.name}.jar")) }*/
    }

    override fun buildDocker() {
        TODO("Not yet implemented")
    }

    override fun buildPterodactyl() {
        TODO("Not yet implemented")
    }

}