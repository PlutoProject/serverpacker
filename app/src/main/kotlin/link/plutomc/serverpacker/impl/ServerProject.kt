package link.plutomc.serverpacker.impl

import link.plutomc.serverpacker.api.Profile
import link.plutomc.serverpacker.api.Project
import java.io.File

class ServerProject(
    override val name: String,
    override val version: String,
    override val authors: List<String>,
    override val buildOutputFolder: File,
    override val workDir: File
): Project {

    private val _profiles: MutableMap<String, Profile> = hashMapOf()

    override val profiles: Map<String, Profile>
        get() = _profiles

    override fun registerProfile(name: String, profile: Profile) {
        _profiles[name] = profile
    }

    override fun buildZip() {
        TODO("Not yet implemented")
    }

    override fun buildDocker() {
        TODO("Not yet implemented")
    }

    override fun buildPterodactyl() {
        TODO("Not yet implemented")
    }

}