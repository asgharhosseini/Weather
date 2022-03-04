package ir.ah.weather.ui.setting

import ir.ah.weather.other.wrapper.FailureInterface

sealed class SettingEvent {
    object LocalNameIsEmpty : SettingEvent()
    object LocalNameIsNotValid : SettingEvent()
    data class NavigateCurrentWeatherFragment(val result: Boolean) : SettingEvent()
    data class ShowError(val failure: FailureInterface?) : SettingEvent()

}