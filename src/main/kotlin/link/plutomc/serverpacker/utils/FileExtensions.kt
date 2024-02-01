package link.plutomc.serverpacker.utils

import org.apache.commons.io.FileUtils
import java.io.File

fun File.dirCheckAndCreate(): File {
    if (!exists()) {
        mkdir()
    }

    return this
}

fun File.fileCheckOrCreate(): File {
    if (!exists()) {
        createNewFile()
    }

    return this
}

fun copyFileOrDirectory(source: File, dest: File) {
    if (source.isDirectory) {
        FileUtils.copyDirectory(source, dest)
    } else {
        FileUtils.copyFile(source, dest)
    }
}