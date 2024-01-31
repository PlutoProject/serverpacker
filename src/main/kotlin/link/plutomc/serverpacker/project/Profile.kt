package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.addition.Configs
import link.plutomc.serverpacker.addition.Plugins
import link.plutomc.serverpacker.source.Source
import java.io.File

class Profile(
    private val name: String,
    private val software: Source,
    val profileDir: File
) {

    private val plugins = Plugins(this)
    private val mods = Plugins(this)
    private val configs = Configs(this)
    private val vmFlags = VMFlags()

    fun buildZip() {

    }

    fun buildDockerImage() {

    }

    fun run() {

    }

}