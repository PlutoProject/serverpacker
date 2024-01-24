package link.plutomc.serverpacker.api

import kotlin.math.pow

val Int.giga
    get() = (this * 2.0.pow(30.0)).toInt()
val Int.mega
    get() = (this * 2.0.pow(20.0)).toInt()
val Int.kilo
    get() = (this * 2.0.pow(10.0)).toInt()