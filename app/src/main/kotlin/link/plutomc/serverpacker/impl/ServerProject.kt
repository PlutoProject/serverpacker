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

    override fun addProfile(profile: Profile) {
        _profiles[profile.name] = profile
    }

    override fun buildZip() {
        _profiles.forEach { it.value.buildZip() }
    }

    override fun buildDocker() {
        _profiles.forEach { it.value.buildDocker() }
    }

    override fun buildPterodactyl() {
        _profiles.forEach { it.value.buildPterodactyl() }
    }

}