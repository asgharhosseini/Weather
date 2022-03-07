package ir.ah.weather.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManagerImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val userInfoManagerImpl: UserInfoManagerImpl
) : BaseViewModel(mainCoroutineDispatcher) {

    fun refreshSetting() = doInMain {

        if (userInfoManagerImpl.getLocal().first().isEmpty()) {
            splashEventChannel.send(SplashEvent.NavigateToSetting(false))
        } else {
            splashEventChannel.send(SplashEvent.NavigateToCurrentWeather(true))
        }
    }

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEvent = splashEventChannel.receiveAsFlow()

    init {
        refreshSetting()
    }
}