package ir.ah.weather.di

import com.bumptech.glide.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import ir.ah.weather.ui.currentweather.adapter.CurrentForecastAdapter
import ir.ah.weather.ui.currentweather.adapter.NextWeatherForecastAdapter


@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {


    @Provides
    fun provideCurrentForecastAdapter(glide: RequestManager) = CurrentForecastAdapter(glide)


    @Provides
    fun provideNextWeatherForecastAdapter(glide: RequestManager) = NextWeatherForecastAdapter(glide)

}