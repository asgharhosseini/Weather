package ir.ah.weather.data.repository.currentweather

import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.other.util.safeApiCall
import ir.ah.weather.other.wrapper.Resource
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CurrentWeatherRepository {
    override suspend fun getCurrentWeather(localName: String): Resource<WeatherResponse> =
        safeApiCall { apiService.getCurrentWeather(localName) }

    override suspend fun getForecastWeather(localName: String): Resource<ForecastResponse> {
        return safeApiCall { apiService.getForecastWeather(localName) }
    }

    override suspend fun getNextWeather(localName: String): Resource<ForecastResponse> {
        return safeApiCall { apiService.getNextWeather(localName) }
    }


}