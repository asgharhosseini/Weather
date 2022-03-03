package ir.ah.weather.ui.currentweather

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher
) : BaseViewModel(mainCoroutineDispatcher) {

}