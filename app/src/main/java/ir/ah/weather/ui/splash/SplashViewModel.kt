package ir.ah.weather.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val mainCoroutineDispatcher: CoroutineDispatcher
) : BaseViewModel(mainCoroutineDispatcher) {

}