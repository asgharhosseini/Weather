package ir.ah.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.ah.weather.BuildConfig
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.data.repository.setting.SettingRepository
import ir.ah.weather.data.repository.setting.SettingRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(apiService: ApiService): SettingRepository =
        if (BuildConfig.DEMO_MODE) SettingRepositoryImpl(apiService) else SettingRepositoryImpl(apiService)



}