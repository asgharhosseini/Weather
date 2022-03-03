package ir.ah.weather.ui.currentweather

import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment

@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<CurrentWeatherViewModel>(
    R.layout.fragment_current_weather,
    CurrentWeatherViewModel::class
) {
}