package ir.ah.weather.ui.setting

import dagger.hilt.android.AndroidEntryPoint
import ir.ah.weather.R
import ir.ah.weather.base.BaseFragment

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel>(
    R.layout.fragment_setting, SettingViewModel::class){
}