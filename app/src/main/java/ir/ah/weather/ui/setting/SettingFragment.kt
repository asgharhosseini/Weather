package ir.ah.weather.ui.setting

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment
import ir.ah.weather.databinding.FragmentSettingBinding
import ir.ah.weather.other.util.afterTextChanged
import ir.ah.weather.other.util.setNavigationResult
import ir.ah.weather.other.viewBinding
import ir.ah.weather.other.wrapper.ApiCallFailure
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel>(
    R.layout.fragment_setting, SettingViewModel::class
) {
    private val binding by viewBinding(FragmentSettingBinding::bind)


    override fun setUpViews() {
        super.setUpViews()
        initView()
        onClickItem()
    }

    private fun onClickItem() {
        binding.letsGoView.setOnClickListener {
            vm.validationLocalName()
        }
    }

    private fun initView() {
        binding.localNameView.afterTextChanged {
            vm.localName.value = it
        }
    }

    override fun observeData() {
        super.observeData()
        lifecycleScope.launchWhenStarted {
            vm.settingEvent.collect { event ->
                when (event) {
                    SettingEvent.LocalNameIsEmpty -> {
                        binding.localNameView.error = getString(R.string.place_name_cannot_be_empty)
                    }
                    SettingEvent.LocalNameIsNotValid -> {
                        binding.localNameView.error = getString(R.string.Location_name_not_found)
                    }

                    is SettingEvent.NavigateCurrentWeatherFragment -> {
                        findNavController().navigate(R.id.action_settingFragment_to_currentWeatherFragment)
                    }
                    is SettingEvent.ShowError -> {

                    }

                }
            }


        }
    }


}