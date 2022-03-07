package ir.ah.weather.ui.setting

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import ir.ah.weather.data.local.UserInfoManagerImpl
import ir.ah.weather.data.repository.setting.SettingRepository
import ir.ah.weather.other.wrapper.ApiCallFailure
import ir.ah.weather.other.wrapper.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher,
    private val settingRepository: SettingRepository,
    private val userInfoManagerImpl: UserInfoManagerImpl
) : BaseViewModel(mainCoroutineDispatcher) {
    val localName: MutableStateFlow<String> = MutableStateFlow("")
    private val settingEventChannel = Channel<SettingEvent>()
    val settingEvent = settingEventChannel.receiveAsFlow()


    fun validationLocalName() {
        val localName = localName.value
        doInMain {
            if (localName.isEmpty()) {
                settingEventChannel.send(SettingEvent.LocalNameIsEmpty)
                return@doInMain
            }

            checkValidLocalName(localName)
            return@doInMain
        }
    }

    private fun checkValidLocalName(localName: String) = doInMain {
        settingRepository.getCheckValidLocalName(localName).let { event ->
            when (event) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    settingEventChannel.send(SettingEvent.NavigateCurrentWeatherFragment(true))
                    userInfoManagerImpl.saveUser(localName)
                    return@doInMain

                }
                is Resource.Failure -> {
                    when (event.failure) {
                        is ApiCallFailure.WeatherError -> {
                            settingEventChannel.send(SettingEvent.LocalNameIsNotValid)
                            return@doInMain

                        }
                        is ApiCallFailure.OtherError -> {
                            settingEventChannel.send(SettingEvent.ShowError(event.failure))
                            return@doInMain

                        }

                    }
                }
            }
        }

    }


}