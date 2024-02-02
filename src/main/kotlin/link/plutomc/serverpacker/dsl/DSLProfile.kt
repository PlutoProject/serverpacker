package link.plutomc.serverpacker.dsl

import link.plutomc.serverpacker.project.StartScript
import link.plutomc.serverpacker.source.Source

class DSLProfile {

    var name: String? = null
    var version: String? = null
    var software: Source? = null
    var plugins = mutableListOf<Source>()
    var mods = mutableListOf<Source>()
    var configs = mutableListOf<Source>()
    var root = mutableListOf<Source>()
    var startScript: StartScript = StartScript()
    var eula = false

    fun plugins(block: DSLFolder.() -> Unit) {
        val dslFolder = DSLFolder()
        dslFolder.block()

        plugins.addAll(dslFolder.contents)
    }

    fun mods(block: DSLFolder.() -> Unit) {
        val dslFolder = DSLFolder()
        dslFolder.block()

        mods.addAll(dslFolder.contents)
    }

    fun configs(block: DSLFolder.() -> Unit) {
        val dslFolder = DSLFolder()
        dslFolder.block()

        configs.addAll(dslFolder.contents)
    }

    fun root(block: DSLFolder.() -> Unit) {
        val dslFolder = DSLFolder()
        dslFolder.block()

        root.addAll(dslFolder.contents)
    }

}