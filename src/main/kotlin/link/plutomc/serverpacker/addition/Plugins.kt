package link.plutomc.serverpacker.addition

import org.apache.commons.io.FileUtils
import java.io.File

class Plugins: Addition {

    private val _contents = hashMapOf<String, File>()

    override val folderName: String
        get() = "plugins"
    override val folder: File
        get() = File(folderName)
    override val contents: Map<String, File>
        get() = _contents

    override fun addContent(name: String, content: File) {
        _contents[name] = content
    }

    override fun removeContent(name: String) {
        _contents.remove(name)
    }

    override fun copyContents() {
        _contents.forEach {
            FileUtils.copyFile(it.value, File(folder, it.value.name))
        }
    }

}