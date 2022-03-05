package ir.ah.weather.data.repository.currentweather

import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.other.wrapper.Resource


interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(
        localName: String,
    ):Resource<WeatherResponse>
    suspend fun getForecastWeather(
        localName: String,
    ):Resource<ForecastResponse>
    suspend fun getNextWeather(
        localName: String,
    ):Resource<ForecastResponse>
}