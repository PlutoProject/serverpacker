package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.source.LocalSource
import link.plutomc.serverpacker.source.Source
import link.plutomc.serverpacker.startScriptCache
import org.apache.commons.io.FileUtils
import java.io.File

class StartScript {

    var javaExec = "java"
    var xmx = ""
    var xms = ""
    var xmn = ""
    var serverMode = true
    var nogui = true
    var flags = arrayListOf<String>()
    var arguments = arrayListOf<String>()
    var jar = "server.jar"

    fun generateWindows(): Source {
        val file = File(startScriptCache, "start_windows.bat")

        if (file.exists()) {
            file.delete()
        }

        file.createNewFile()

        val script = StringBuilder()
        script.append("@echo off\n")

        appendStartCommand(script)

        val string = script.toString()
        FileUtils.writeStringToFile(file, string, "UTF-8")

        return LocalSource(file)
    }

    fun generateUnixOrLinux(): Source {
        val file = File(startScriptCache, "start_linux_unix.sh")

        if (file.exists()) {
            file.delete()
        }

        file.createNewFile()

        val script = StringBuilder()
        script.append("#!/usr/bin/env bash\n")

        appendStartCommand(script)

        val string = script.toString()
        FileUtils.writeStringToFile(file, string, "UTF-8")

        return LocalSource(file)
    }

    private fun appendStartCommand(script: StringBuilder) {
        script.append(getStartCmd())
    }

    private fun getStartCmd(): String {
        val script = StringBuilder()

        script.append("$javaExec ")

        if (xmx != "") {
            script.append("-Xmx$xmx ")
        }

        if (xms != "") {
            script.append("-Xms$xms ")
        }

        if (xmn != "") {
            script.append("-Xmn$xmn ")
        }

        flags.forEach {
            script.append("-$it ")
        }

        if (serverMode) {
            script.append("-server ")
        }

        script.append("$jar ")

        if (nogui) {
            script.append("nogui ")
        }

        arguments.forEach {
            script.append("$it ")
        }

        return script.toString()
    }

    fun run() {
        TODO("Run script.")
    }

}