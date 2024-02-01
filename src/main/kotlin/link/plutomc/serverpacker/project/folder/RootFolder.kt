package link.plutomc.serverpacker.project.folder

import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.source.Source
import link.plutomc.serverpacker.utils.copyFileOrDirectory
import java.io.File

class RootFolder(private val profile: Profile) : IFolder {

    private val _contents = arrayListOf<Source>()

    override val name: String
        get() = profile.profileDir.name
    override val actual: File
        get() = profile.profileDir
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