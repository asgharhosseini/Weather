package ir.ah.weather.ui.splash

import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(
    R.layout.fragment_splash, SplashViewModel::class){
}