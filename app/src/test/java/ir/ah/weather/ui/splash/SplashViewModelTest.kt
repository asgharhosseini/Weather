package ir.ah.weather.ui.splash

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import io.kotest.core.config.Defaults.testCoroutineDispatcher
import io.kotest.matchers.shouldBe
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.local.UserInfoManagerImpl
import ir.ah.weather.data.repository.setting.SettingRepository
import ir.ah.weather.data.repository.splash.SplashRepository
import ir.ah.weather.ui.MainCoroutineRule
import ir.ah.weather.ui.setting.SettingEvent
import ir.ah.weather.ui.setting.SettingViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime
@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var vm: SplashViewModel

    lateinit var CoroutineDispatcher: TestCoroutineDispatcher

    @Mock
    lateinit var splashRepository: SplashRepository

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this);
       // CoroutineDispatcher = TestCoroutineDispatcher()
        //vm = SplashViewModel(CoroutineDispatcher,splashRepository)


    }



    @Test
    fun getLocalNameIsEmptySplashEventNavigateToSetting() = runBlockingTest  {

        CoroutineDispatcher = TestCoroutineDispatcher()
        vm = SplashViewModel(CoroutineDispatcher,splashRepository)
        `when`(splashRepository.getLocal()?.first()).thenReturn("")

        vm.refreshSetting()

        vm.splashEvent.test {
            expectItem() shouldBe SplashEvent.NavigateToSetting(false)
        }

    }
    @Test
    fun `get local Name is not empty Splash Event Navigate To Current Weather`() = runBlockingTest {

        `when`(splashRepository.getLocal()?.first()).thenReturn("tehran")

        vm.refreshSetting()
        vm.splashEvent.test {
            expectItem() shouldBe SplashEvent.NavigateToCurrentWeather(true)
        }

    }


}