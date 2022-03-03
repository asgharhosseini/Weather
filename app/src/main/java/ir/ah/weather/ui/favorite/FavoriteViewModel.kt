package ir.ah.weather.ui.favorite

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.weather.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    mainCoroutineDispatcher: CoroutineDispatcher
) : BaseViewModel(mainCoroutineDispatcher) {

}