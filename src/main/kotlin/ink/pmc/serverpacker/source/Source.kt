package ink.pmc.serverpacker.source

import java.io.File

interface Source {

    val file: File
    val url: String

    fun resolve(): Boolean

}