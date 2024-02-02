package link.plutomc.serverpacker.dsl

import link.plutomc.serverpacker.dsl.source.DSLLocalSource
import link.plutomc.serverpacker.dsl.source.DSLModrinthSource
import link.plutomc.serverpacker.dsl.source.DSLNetworkSource
import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.source.LocalSource
import link.plutomc.serverpacker.source.ModrinthSource
import link.plutomc.serverpacker.source.NetworkSource
import link.plutomc.serverpacker.source.Source

class DSLFolder {

    val contents = mutableListOf<Source>()

    fun modrinth(block: DSLModrinthSource.() -> Unit) {
        val dslModrinthSource = DSLModrinthSource()
        dslModrinthSource.block()

        if ((dslModrinthSource.projectId == null && dslModrinthSource.gameVersion == null) &&
            dslModrinthSource.versionId == null
        ) {
            logger.error("You haven't configure a Modrinth source's properties!")
            return
        }

        if (dslModrinthSource.versionId != null) {
            contents.add(ModrinthSource(checkNotNull(dslModrinthSource.versionId)))
            return
        }

        contents.add(
            ModrinthSource(
                checkNotNull(dslModrinthSource.projectId),
                checkNotNull(dslModrinthSource.gameVersion)
            )
        )
    }

    fun local(block: DSLLocalSource.() -> Unit) {
        val dslLocalSource = DSLLocalSource()
        dslLocalSource.block()

        if (dslLocalSource.file == null) {
            logger.error("You haven't configure a local source's file!")
            return
        }

        contents.add(LocalSource(checkNotNull(dslLocalSource.file)))
    }

    fun network(block: DSLNetworkSource.() -> Unit) {
        val dslNetworkSource = DSLNetworkSource()
        dslNetworkSource.block()

        if (dslNetworkSource.url == null) {
            logger.error("You haven't configure a network source's url!")
            return
        }

        if (dslNetworkSource.customFileName == null) {
            contents.add(NetworkSource(checkNotNull(dslNetworkSource.url)))
            return
        }

        contents.add(NetworkSource(checkNotNull(dslNetworkSource.url), checkNotNull(dslNetworkSource.customFileName)))
    }

}