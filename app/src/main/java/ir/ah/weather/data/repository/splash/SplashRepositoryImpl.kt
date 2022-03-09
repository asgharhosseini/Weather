package ir.ah.weather.data.repository.splash

import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.local.UserInfoManagerImpl
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.other.util.safeApiCall
import ir.ah.weather.other.wrapper.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val userInfoManager: UserInfoManager) :
    SplashRepository {
    override suspend fun saveUser(local: String) = userInfoManager.saveUser(local)
    override suspend fun getLocal(): Flow<String>? = userInfoManager.getLocal()


}