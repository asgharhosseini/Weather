package ir.ah.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.ah.weather.BuildConfig
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.remote.ApiService
import ir.ah.weather.data.repository.NextWeather.NextWeatherRepository
import ir.ah.weather.data.repository.NextWeather.NextWeatherRepositoryImpl
import ir.ah.weather.data.repository.currentweather.CurrentWeatherRepository
import ir.ah.weather.data.repository.currentweather.CurrentWeatherRepositoryImpl
import ir.ah.weather.data.repository.setting.SettingRepository
import ir.ah.weather.data.repository.setting.SettingRepositoryImpl
import ir.ah.weather.data.repository.splash.SplashRepository
import ir.ah.weather.data.repository.splash.SplashRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(apiService: ApiService): SettingRepository =
        if (BuildConfig.DEMO_MODE) SettingRepositoryImpl(apiService) else SettingRepositoryImpl(
            apiService
        )

    @Provides
    @Singleton
    internal fun provideCurrentWeatherRepository(apiService: ApiService): CurrentWeatherRepository =
        if (BuildConfig.DEMO_MODE) CurrentWeatherRepositoryImpl(apiService) else CurrentWeatherRepositoryImpl(
            apiService
        )

    @Provides
    @Singleton
    internal fun provideNextWeatherRepository(apiService: ApiService): NextWeatherRepository =
        if (BuildConfig.DEMO_MODE) NextWeatherRepositoryImpl(apiService) else NextWeatherRepositoryImpl(
            apiService
        )

    @Provides
    @Singleton
    internal fun provideSplashRepository(userInfoManager :UserInfoManager): SplashRepository =
        if (BuildConfig.DEMO_MODE) SplashRepositoryImpl(userInfoManager) else SplashRepositoryImpl(
            userInfoManager
        )


}