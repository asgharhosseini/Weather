package ir.ah.weather.ui.favorite

import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment
@AndroidEntryPoint
class FavoriteFragment: BaseFragment<FavoriteViewModel>(
    R.layout.fragment_favorite, FavoriteViewModel::class) {
}