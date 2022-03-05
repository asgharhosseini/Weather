package ir.ah.weather.data.repository.NextWeather

import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.other.util.safeApiCall
import ir.ah.weather.other.wrapper.Resource
import javax.inject.Inject

class NextWeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NextWeatherRepository {

    override suspend fun getNextWeather(localName: String): Resource<ForecastResponse> {
        return safeApiCall { apiService.getNextWeather(localName) }
    }


}