package ir.ah.weather.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment
import ir.ah.weather.databinding.FragmentSplashBinding
import ir.ah.weather.other.util.getNavigationResult
import ir.ah.weather.other.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(
    R.layout.fragment_splash, SplashViewModel::class){
    private val binding by viewBinding(FragmentSplashBinding::bind)


    override fun observeData() {
        super.observeData()
        lifecycleScope.launchWhenStarted {
            delay(2000)
            vm.splashEvent.collect { event ->

                when (event) {
                    is SplashEvent.NavigateToCurrentWeather -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToCurrentWeatherFragment())
                    }
                    is SplashEvent.NavigateToSetting -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSettingFragment())
                    }
                }
            }
        }
    }


    override fun setUpViews() {
        super.setUpViews()
    }
}