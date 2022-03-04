package ir.ah.weather.data.remote

import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.di.EndPoint
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(EndPoint.getCheckValidLocalName)
    suspend fun getCheckValidLocalName(
        @Query("q")
        localName:String,
        @Query("appid")
        apiKey: String=EndPoint.apiKey,
        @Query("lang")
        lang: String = EndPoint.lang,
        @Query("units")
        units: String = EndPoint.units
    ): Response<WeatherResponse>
}