package ink.pmc.serverpacker.project.folder

import ink.pmc.serverpacker.project.Profile
import ink.pmc.serverpacker.source.Source
import ink.pmc.serverpacker.utils.copyFileOrDirectory
import ink.pmc.serverpacker.utils.dirCheckAndCreate
import java.io.File

class Folder(override val name: String, private val profile: Profile) : IFolder {

    private val _contents = arrayListOf<Source>()

    override val actual: File
        get() = File(profile.profileDir, name).dirCheckAndCreate()
    override val contents: List<Source>
        get() = _contents

    override fun copyContents() {
        _contents.forEach {
            copyFileOrDirectory(it.file, File(actual, it.file.name))
        }
    }

    override fun addContent(source: Source) {
        if (!_contents.contains(source)) {
            _contents.add(source)
        }
    }

}