package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.addition.Configs
import link.plutomc.serverpacker.addition.Mods
import link.plutomc.serverpacker.addition.Plugins
import link.plutomc.serverpacker.source.Source
import java.io.File

class Profile(
    private val name: String,
    private val software: Source,
    val profileDir: File
) {

    private val plugins = Plugins(this)
    private val mods = Mods(this)
    private val configs = Configs(this)
    val vmFlags = "java -jar ${software.file?.name}"

    fun copyContents() {
        plugins.copyContents()
        mods.copyContents()
        configs.copyContents()
    }

}