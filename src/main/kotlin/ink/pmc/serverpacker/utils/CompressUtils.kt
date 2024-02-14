package ink.pmc.serverpacker.utils

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
import org.apache.commons.io.FileUtils
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry

object CompressUtils {

    fun compressFolderContentsToZip(folder: File, zip: File) {
        ZipArchiveOutputStream(BufferedOutputStream(FileOutputStream(zip))).use { zipOut ->

            zipOut.setLevel(5)
            zipOut.setMethod(ZipEntry.DEFLATED)

            scanFolder(folder).forEach {
                val path = folder.toPath().relativize(it.toPath())
                val entry = ZipArchiveEntry(path.toString())

                zipOut.putArchiveEntry(entry)
                FileUtils.copyFile(it, zipOut)
                zipOut.closeArchiveEntry()
            }

        }
    }

    private fun scanFolder(folder: File): List<File> {

        val files = mutableListOf<File>()

        folder.listFiles()!!.forEach {
            if (it.isFile) {
                files.add(it)
            } else {
                files.addAll(scanFolder(it))
            }
        }

        return files
    }

}