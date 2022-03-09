package ir.ah.weather.data.repository.splash

import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.other.wrapper.Resource
import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    suspend fun saveUser(local: String)
    suspend fun getLocal(): Flow<String>?

}