package ir.ah.weather.data.repository.setting

import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.other.util.safeApiCall
import ir.ah.weather.other.wrapper.Resource
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    SettingRepository {

    override suspend fun getCheckValidLocalName(localName: String): Resource<WeatherResponse> =
    safeApiCall{ apiService.getCheckValidLocalName(localName) }

}