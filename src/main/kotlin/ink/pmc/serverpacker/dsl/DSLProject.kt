package ink.pmc.serverpacker.dsl

import ink.pmc.serverpacker.logger
import ink.pmc.serverpacker.project.Profile
import ink.pmc.serverpacker.project.Project
import ink.pmc.serverpacker.utils.dirCheckAndCreate
import java.io.File

fun project(block: DSLProject.() -> Unit): Project? {
    val dslProject = DSLProject()
    dslProject.block()

    if (dslProject.name == null) {
        logger.error("You haven't set project's name!")
        return null
    }

    val project = Project(checkNotNull(dslProject.name))

    dslProject.profiles.forEach {
        project.addProfile(it)
    }

    return project
}

class DSLProject {

    var name: String? = null
    val profiles = mutableListOf<Profile>()

    fun profile(block: DSLProfile.() -> Unit) {
        val dslProfile = DSLProfile()
        dslProfile.block()

        if (dslProfile.name == null) {
            logger.error("You haven't set profile's name!")
            return
        }

        if (dslProfile.version == null) {
            logger.error("You haven't set profile's version!")
            return
        }

        if (dslProfile.software == null) {
            logger.error("You haven't set profile's software!")
            return
        }

        val profile = Profile(
            checkNotNull(dslProfile.name),
            checkNotNull(dslProfile.version),
            checkNotNull(dslProfile.software),
            File(ink.pmc.serverpacker.profileCacheDir, "$name-${ink.pmc.serverpacker.version}/").dirCheckAndCreate()
        )

        dslProfile.plugins.forEach {
            profile.plugins.addContent(it)
        }

        dslProfile.mods.forEach {
            profile.mods.addContent(it)
        }

        dslProfile.configs.forEach {
            profile.configs.addContent(it)
        }

        dslProfile.root.forEach {
            profile.root.addContent(it)
        }

        profile.startScript = dslProfile.startScript
        profile.eula = dslProfile.eula

        profiles.add(profile)
    }

}