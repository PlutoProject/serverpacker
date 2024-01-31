package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.addition.Configs
import link.plutomc.serverpacker.addition.Mods
import link.plutomc.serverpacker.addition.Plugins
import link.plutomc.serverpacker.source.Source
import java.io.File

class Profile(
    private val name: String,
    private val version: String,
    private val software: Source,
    val profileDir: File
) {

    var plugins = Plugins(this)
    var mods = Mods(this)
    var configs = Configs(this)

    fun copyContents() {
        plugins.copyContents()
        mods.copyContents()
        configs.copyContents()
    }

}