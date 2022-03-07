package ir.ah.weather.data.local

import android.content.*
import javax.inject.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoManagerImpl @Inject constructor(
    private val userPreferencesDataStore: DataStore<Preferences>
) : UserInfoManager {

    private val USER_LOCAL_NAME = stringPreferencesKey("user_local_name")

    override suspend fun saveUser(local: String) {
        userPreferencesDataStore.edit { preferences ->
            preferences[USER_LOCAL_NAME] = local
        }

    }


    override suspend fun getLocal(): Flow<String> = userPreferencesDataStore.data
        .map {
            it[USER_LOCAL_NAME] ?: ""
        }


}