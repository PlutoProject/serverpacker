package link.plutomc.serverpacker.addition

import java.io.File

interface Addition {

    val folderName: String
    val folder: File
    val contents: Map<String, File>

    fun addContent(name: String, content: File)

    fun removeContent(name: String)

    fun copyContents()

}