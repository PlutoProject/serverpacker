package link.plutomc.serverpacker.project.folder

import link.plutomc.serverpacker.source.Source
import java.io.File

interface IFolder {

    val name: String
    val actual: File
    val contents: List<Source>

    fun copyContents()

    fun addContent(source: Source)

}