package link.plutomc.serverpacker.api

import java.io.File

class VMOptions() {

    lateinit var jarLocation: File
    lateinit var javaLocation: File
    var xmx: Int = 0
    var xms: Int = 0
    var xmn: Int = 0
    var xss: Int = 0
    var serverMode: Boolean = false

    val properties: MutableMap<String, String> = hashMapOf()
    val flags: MutableMap<String, String> = hashMapOf()
    val nonstandardFlags: MutableMap<String, String> = hashMapOf()

    fun generateCommand(): String {
        val builder = StringBuilder()

        if (!::jarLocation.isInitialized) {
            return ""
        }

        if (::javaLocation.isInitialized) {
            builder.append("${javaLocation.absolutePath} ")
        } else {
            builder.append("java ")
        }

        builder.append("-jar ")

        if (xmx > 0) {
            builder.append("-Xmx$xmx ")
        }

        if (xms > 0) {
            builder.append("-Xms$xms ")
        }

        if (xmn > 0) {
            builder.append("-Xmn$xmn ")
        }

        if (xss > 0) {
            builder.append("-Xss$xss ")
        }

        flags.forEach {
            builder.append("-X${it.key}=${it.value} ")
        }

        nonstandardFlags.forEach {
            builder.append("-XX:${it.key}=${it.value} ")
        }

        properties.forEach {
            builder.append("-D${it.key}=${it.value} ")
        }

        if (serverMode) {
            builder.append("-server ")
        }

        builder.append(jarLocation.path)

        return builder.toString()
    }

}