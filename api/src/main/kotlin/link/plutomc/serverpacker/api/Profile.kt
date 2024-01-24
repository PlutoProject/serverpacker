package link.plutomc.serverpacker.api

import link.plutomc.serverpacker.api.plugin.Plugin

interface Profile: Buildable {

    val name: String

    val plugins: Collection<Plugin>
    val configurations: Collection<Configuration>
    val files: Collection<File>

    fun addPlugin(plugin: Plugin)

    fun addConfiguration(config: Configuration)

    fun addFile(file: File)

}