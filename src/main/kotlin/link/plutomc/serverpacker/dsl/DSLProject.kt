package link.plutomc.serverpacker.dsl

import link.plutomc.serverpacker.project.Project


fun project(block: Project.() -> Unit): Project {
    val project = Project()
    project.block()
    return project
}