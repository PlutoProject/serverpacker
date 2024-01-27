package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.addition.Configs
import link.plutomc.serverpacker.addition.Plugins
import link.plutomc.serverpacker.source.Source
import java.io.File

class Profile(
    private val name: String,
    private val software: Source,
    private val profileDir: File
) {

    private val plugins = Plugins(profileDir)
    private val mods = Plugins(profileDir)
    private val configs = Configs(profileDir)

}