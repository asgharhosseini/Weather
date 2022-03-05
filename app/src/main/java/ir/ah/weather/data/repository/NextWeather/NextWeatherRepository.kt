package ir.ah.weather.data.repository.NextWeather

import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.other.wrapper.Resource


interface NextWeatherRepository {
    suspend fun getNextWeather(
        localName: String,
    ):Resource<ForecastResponse>
}