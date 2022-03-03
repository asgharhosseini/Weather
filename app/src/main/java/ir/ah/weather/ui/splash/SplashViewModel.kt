package ir.ah.weather.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val userInfoManager: UserInfoManager
) : BaseViewModel(mainCoroutineDispatcher) {

    fun refreshSetting() = doInMain {
        if (userInfoManager.getLocal().isNullOrEmpty()&&
            userInfoManager.getLocal().isNullOrEmpty()
        ) {
            splashEventChannel.send(SplashEvent.NavigateToSetting(false))
        }else{
            splashEventChannel.send(SplashEvent.NavigateToCurrentWeather(true))
        }
    }

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEvent = splashEventChannel.receiveAsFlow()

    init {
        refreshSetting()
    }
}