package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.source.Source

class RunScript {

    private val sources: MutableList<Source> = arrayListOf()

    fun getWindows(): Source {
        return sources[0]
    }

    fun getLinux(): Source {
        return sources[1]
    }

    fun getMacOS(): Source {
        return sources[2]
    }

}