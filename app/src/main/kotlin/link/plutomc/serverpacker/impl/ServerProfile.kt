package link.plutomc.serverpacker.impl

import link.plutomc.serverpacker.api.Configuration
import link.plutomc.serverpacker.api.File
import link.plutomc.serverpacker.api.Profile
import link.plutomc.serverpacker.api.Project
import link.plutomc.serverpacker.api.plugin.Plugin

class ServerProfile(
    private val project: Project,
    override val name: String
): Profile {

    private val _plugins: MutableList<Plugin> = mutableListOf()
    private val _configurations: MutableList<Configuration> = mutableListOf()
    private val _files: MutableList<File> = mutableListOf()

    override val plugins: Collection<Plugin>
        get() = _plugins
    override val configurations: Collection<Configuration>
        get() = _configurations
    override val files: Collection<File>
        get() = _files

    override fun addPlugin(plugin: Plugin) {
        _plugins.add(plugin)
    }

    override fun addConfiguration(config: Configuration) {
        _configurations.add(config)
    }

    override fun addFile(file: File) {
        _files.add(file)
    }

    override fun buildZip() {
        TODO("Not yet implemented")
    }

    override fun buildDocker() {
        TODO("Not yet implemented")
    }

    override fun buildPterodactyl() {
        TODO("Not yet implemented")
    }

}