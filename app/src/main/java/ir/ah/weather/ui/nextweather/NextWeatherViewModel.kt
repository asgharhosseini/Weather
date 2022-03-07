package ir.ah.weather.ui.nextweather

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManagerImpl
import ir.ah.weather.data.model.ForecastResponse
import ir.ah.weather.data.repository.NextWeather.NextWeatherRepository
import ir.ah.weather.other.wrapper.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NextWeatherViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val repository: NextWeatherRepository,
    private val userInfoManagerImpl: UserInfoManagerImpl
) : BaseViewModel(mainCoroutineDispatcher) {
    private val nextWeatherForecastChanel = Channel<Resource<ForecastResponse>>()
    val nextWeatherForecast = nextWeatherForecastChanel.receiveAsFlow()


    fun getNextWeather() = doInMain {
        nextWeatherForecastChanel.send(Resource.Loading)
        nextWeatherForecastChanel.send(
            repository.getNextWeather(
                userInfoManagerImpl.getLocal().toString()
            )
        )

    }

}