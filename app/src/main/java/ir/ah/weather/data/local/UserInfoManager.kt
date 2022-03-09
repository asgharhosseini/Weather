package ir.ah.weather.data.local

import kotlinx.coroutines.flow.Flow

interface UserInfoManager {

    suspend fun saveUser(local: String)
    suspend fun getLocal(): Flow<String>

}