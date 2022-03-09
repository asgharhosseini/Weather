package ir.ah.weather.ui.setting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.model.*
import ir.ah.weather.data.repository.setting.SettingRepository
import ir.ah.weather.other.wrapper.ApiCallFailure
import ir.ah.weather.other.wrapper.Resource
import ir.ah.weather.ui.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SettingViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Mock
    lateinit var settingRepository: SettingRepository

    lateinit var vm: SettingViewModel

    lateinit var CoroutineDispatcher: TestCoroutineDispatcher

    @Mock
    lateinit var userInfoManager: UserInfoManager

    @Before
    fun createDatastore() {

        MockitoAnnotations.initMocks(this);

        CoroutineDispatcher = TestCoroutineDispatcher()
        vm = SettingViewModel(CoroutineDispatcher, settingRepository, userInfoManager)
    }


    @Test
    fun emptyLocalNameReturnLocalNameIsEmpty() = runBlockingTest {
        vm.localName.value = ""
        vm.validationLocalName()
        vm.settingEvent.test {
            expectItem() shouldBe SettingEvent.LocalNameIsEmpty
        }
    }


    @Test
    @Throws(Exception::class)
    fun emptyLocalNameReturnLocalNameIsNotValid() = runBlockingTest {
        vm.localName.value = "sdfsf"
        val failedError = ApiCallFailure.WeatherError("test")
        val returnedValue: Resource<WeatherResponse> = Resource.Failure(failedError)
        `when`(settingRepository.getCheckValidLocalName(vm.localName.value)).thenReturn(
            returnedValue
        )
        vm.validationLocalName()
      //  verify(settingRepository).getCheckValidLocalName(eq(vm.localName.value))


        vm.settingEvent.test {
            expectItem() shouldBe SettingEvent.LocalNameIsNotValid
        }
    }


    fun getWeatherResponse(): WeatherResponse {
        return WeatherResponse(
            base = "stations",
            clouds = Clouds(100),
            cod = 200,
            coord = Coord(lon = 54.42, lat = 35.69), dt = 1646635721, id = 47737,
            main = Main(
                temp = 13.88,
                feelsLike = 11.66,
                tempMin = 11.85,
                tempMax = 13.88,
                pressure = 1011,
                humidity = 13,
            ),
            name = "تهران",
            sys = Sys(
                country = "String",
                id = 1,
                sunrise = 1,
                sunset = 1,
                type = 1
            ),
            timezone = 12600,
            visibility = 10000,
            weather = arrayListOf<Weather>(
                Weather(
                    id = 803,
                    main = "Clouds",
                    description = "ابرهای پارچه پارچه شده",
                    icon = "04n"
                )
            ),
            wind = Wind(speed = 8.73, deg = 291)
        )

    }


}