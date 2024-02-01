package link.plutomc.serverpacker.dsl

import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.project.Project

fun Project.profile(block: Profile.() -> Unit) {
    val profile = Profile()
    profile.block()

    this.profiles.add(profile)
}