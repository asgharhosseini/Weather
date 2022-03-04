package ir.ah.weather.data.repository.setting

import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.other.wrapper.Resource

interface SettingRepository {
    suspend fun getCheckValidLocalName(localName:String): Resource<WeatherResponse>
}