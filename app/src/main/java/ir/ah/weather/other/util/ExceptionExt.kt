package ir.ah.weather.other.util

import ir.ah.weather.BuildConfig

fun Exception.print() {
    if (BuildConfig.DEBUG)
        printStackTrace()
}

fun Throwable.print() {
    if (BuildConfig.DEBUG)
        printStackTrace()
}

