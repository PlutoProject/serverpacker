package link.plutomc.serverpacker.utils

class Timer {

    private val startTime = System.currentTimeMillis()

    fun endTimer(): Long {
        return System.currentTimeMillis() - startTime
    }

}