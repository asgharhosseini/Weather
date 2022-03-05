package ir.ah.weather.ui.nextweather

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.MainActivity
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment
import ir.ah.weather.data.model.WeatherResponse
import ir.ah.weather.databinding.FragmentCurrentWeatherBinding
import ir.ah.weather.databinding.FragmentNextWeatherBinding
import ir.ah.weather.di.EndPoint
import ir.ah.weather.other.util.ForecastMapper
import ir.ah.weather.other.viewBinding
import ir.ah.weather.other.wrapper.Resource
import ir.ah.weather.ui.currentweather.adapter.CurrentForecastAdapter
import ir.ah.weather.ui.currentweather.adapter.NextWeatherForecastAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NextWeatherFragment : BaseFragment<NextWeatherViewModel>(
    R.layout.fragment_next_weather, NextWeatherViewModel::class){
    private val binding by viewBinding(FragmentNextWeatherBinding::bind)

    @Inject
    lateinit var nextWeatherForecastAdapter: NextWeatherForecastAdapter



    @Inject
    lateinit var forecastMapper: ForecastMapper



    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).showBottomNav()
        setUpAdapter()

    }

    private fun setUpAdapter() {

        binding.nextWeatherForecastRecyclerView.apply {
            adapter = nextWeatherForecastAdapter
            layoutManager =
                GridLayoutManager(requireContext(),3)
        }
    }

    override fun observeData() {
        super.observeData()
        subscribeToObserve()
    }

    private fun subscribeToObserve() {

        vm.getNextWeather()

        lifecycleScope.launchWhenStarted {
            vm.nextWeatherForecast.collect { event ->
                handleResource(event) { vm.getNextWeather() }
                when (event) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Log.e("sdasdasd", event.success.dayWeather?.size.toString())
                        event.success.dayWeather?.let {
                            Log.e("sdasdasd", forecastMapper.mapFrom(it).size.toString())
                            nextWeatherForecastAdapter.submitList(forecastMapper.mapFrom(it))

                        }



                    }
                    is Resource.Failure -> {

                    }
                }

            }
        }
    }

}