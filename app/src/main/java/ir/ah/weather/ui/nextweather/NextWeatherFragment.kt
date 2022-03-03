package ir.ah.weather.ui.nextweather

import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment

@AndroidEntryPoint
class NextWeatherFragment : BaseFragment<NextWeatherViewModel>(
    R.layout.fragment_next_weather, NextWeatherViewModel::class){
}