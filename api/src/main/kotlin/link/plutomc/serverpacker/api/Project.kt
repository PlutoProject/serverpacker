package link.plutomc.serverpacker.api

import java.io.File

interface Project: Buildable {

    val name: String
    val version: String
    val authors: List<String>
    val profiles: Map<String, Profile>
    val buildOutputFolder: File
    val workDir: File

    fun registerProfile(name: String, profile: Profile)

}