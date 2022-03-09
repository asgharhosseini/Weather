package ir.ah.weather.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ir.ah.weather.data.repository.FackSplashRepository

import ir.ah.weather.ui.splash.SplashFragment
import ir.ah.weather.ui.splash.SplashViewModel
import kotlinx.coroutines.test.TestCoroutineDispatcher

import javax.inject.Inject

class TestFragmentFactory @Inject constructor(

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SplashFragment::class.java.name -> SplashFragment()

            else -> super.instantiate(classLoader, className)
        }
    }
}