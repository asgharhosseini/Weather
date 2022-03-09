package ir.ah.weather.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.local.UserInfoManagerImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreServiceModule {

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "userInfo"
    )

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext app: Context
    ): DataStore<Preferences> = app.userPreferencesDataStore
    @Singleton
    @Provides
    fun provideUserInfoManager(userPreferencesDataStore: DataStore<Preferences>): UserInfoManager =
        UserInfoManagerImpl(userPreferencesDataStore)


}