package ir.ah.weather.ui.currentweather

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.data.repository.currentweather.CurrentWeatherRepository
import ir.ah.weather.other.wrapper.ApiCallFailure
import ir.ah.weather.other.wrapper.Resource
import ir.ah.weather.ui.setting.SettingEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val repository: CurrentWeatherRepository,
    private val userInfoManager: UserInfoManager
) : BaseViewModel(mainCoroutineDispatcher) {

    private val currentWeatherChanel = Channel<Resource<WeatherResponse>>()
    val currentWeather = currentWeatherChanel.receiveAsFlow()
    private val currentForecastChanel = Channel<Resource<ForecastResponse>>()
    val currentForecast = currentForecastChanel.receiveAsFlow()
    private val nextWeatherForecastChanel = Channel<Resource<ForecastResponse>>()
    val nextWeatherForecast = nextWeatherForecastChanel.receiveAsFlow()


    fun getCurrentWeather() = doInMain {
        currentWeatherChanel.send(Resource.Loading)
        currentWeatherChanel.send(
            repository.getCurrentWeather(
                userInfoManager.getLocal().toString()
            )
        )
    }

    fun getForecastWeather() = doInMain {
        currentForecastChanel.send(Resource.Loading)
        currentForecastChanel.send(
            repository.getForecastWeather(
                userInfoManager.getLocal().toString()
            )
        )
    }

    fun getNextWeather() = doInMain {
        nextWeatherForecastChanel.send(Resource.Loading)
        nextWeatherForecastChanel.send(
            repository.getNextWeather(
                userInfoManager.getLocal().toString()
            )
        )
        repository.getNextWeather(
            userInfoManager.getLocal().toString()
        ).failure.let {
            when(it){
                is ApiCallFailure.OtherError->{
                    Log.e("sdasdasd",   it.errorMessage.toString())


                }
            }
        }
    }


}