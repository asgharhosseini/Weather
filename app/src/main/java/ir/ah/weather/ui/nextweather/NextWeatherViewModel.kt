package ir.ah.weather.ui.nextweather

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.repository.NextWeather.NextWeatherRepository
import ir.ah.weather.data.repository.NextWeather.NextWeatherRepositoryImpl
import ir.ah.weather.other.wrapper.ApiCallFailure
import ir.ah.weather.other.wrapper.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NextWeatherViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val repository: NextWeatherRepository,
    private val userInfoManager: UserInfoManager
) : BaseViewModel(mainCoroutineDispatcher) {
    private val nextWeatherForecastChanel = Channel<Resource<ForecastResponse>>()
    val nextWeatherForecast = nextWeatherForecastChanel.receiveAsFlow()


    fun getNextWeather() = doInMain {
        nextWeatherForecastChanel.send(Resource.Loading)
        nextWeatherForecastChanel.send(
            repository.getNextWeather(
                userInfoManager.getLocal().toString()
            )
        )

    }

}