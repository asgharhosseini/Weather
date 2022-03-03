package ir.ah.weather.ui.splash

sealed class SplashEvent {

    data class NavigateToSetting(val result: Boolean) : SplashEvent()
    data class NavigateToCurrentWeather(val result: Boolean) : SplashEvent()

}