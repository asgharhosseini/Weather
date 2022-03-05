package ir.ah.weather.other.util

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}