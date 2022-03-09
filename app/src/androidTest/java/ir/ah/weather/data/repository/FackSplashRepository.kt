package ir.ah.weather.data.repository

import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.repository.splash.SplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FackSplashRepository @Inject constructor(private val userInfoManager: UserInfoManager):SplashRepository{


    override suspend fun saveUser(local: String) {
        userInfoManager.saveUser(local)
    }

    override suspend fun getLocal(): Flow<String>? =
        userInfoManager.getLocal()

}