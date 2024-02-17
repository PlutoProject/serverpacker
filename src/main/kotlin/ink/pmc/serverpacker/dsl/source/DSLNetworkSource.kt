package ink.pmc.serverpacker.dsl.source

import ink.pmc.serverpacker.source.NetworkSource
import ink.pmc.serverpacker.source.Source

fun networkSource(url: String, customFileName: String = ""): Source {
    return NetworkSource(url, customFileName)
}

class DSLNetworkSource {

    var url: String? = null
    var customFileName: String? = null

}