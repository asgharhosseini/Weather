package ir.ah.weather.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.ah.weather.FakeNetworkConnectionInterceptor
import ir.ah.weather.MainActivity
import ir.ah.weather.R
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.data.model.*
import ir.ah.weather.data.repository.FackSplashRepository
import ir.ah.weather.data.repository.splash.SplashRepository
import ir.ah.weather.other.wrapper.Resource
import ir.ah.weather.ui.setting.SettingFragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import javax.inject.Inject

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class SplashFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var moshi: Moshi

    @Inject
    lateinit var fakeNetworkConnectionInterceptor: FakeNetworkConnectionInterceptor

    @Inject
    lateinit var mainCoroutineDispatcher: CoroutineDispatcher

    private val feedApiResponseJsonAdapter get() = moshi.adapter(WeatherResponse::class.java)

    lateinit var splashViewModel: SplashViewModel
    lateinit var splashRepository: SplashRepository

    @Inject
    lateinit var userInfoManager: UserInfoManager

    @Before
    fun setup() {
        hiltRule.inject()
        splashRepository = FackSplashRepository(userInfoManager)
        splashViewModel = SplashViewModel(mainCoroutineDispatcher, splashRepository)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        (mainCoroutineDispatcher as TestCoroutineDispatcher).cleanupTestCoroutines()

    }

    private fun enqueueSuccessResponse() = runBlocking {
//        mockWebServer.start(port = 57594)
//        mockWebServer.enqueue(
//            MockResponse().setResponseCode(200)
//                .setBody(feedApiResponseJsonAdapter.toJson(getWeatherResponse()))
//        )


    }


//    @Test
//    fun checkVisibilityUI() {
//
//        enqueueSuccessResponse()
//        launchFragmentInHiltContainer<SettingFragment>()
//        onView(withId(R.id.appTitle)).check(matches(isDisplayed()))
//        onView(withId(R.id.appDescription)).check(matches(isDisplayed()))
//        onView(withId(R.id.appSite)).check(matches(isDisplayed()))
//        onView(withId(R.id.animationView)).check(matches(isDisplayed()))
////        onView(withId(R.id.localNameView)).check(matches(isDisplayed()))
////
//
//
//    }
//

    @Test
    fun testNavigationToSecondScreen() {
        // Create a mock NavController
        val mockNavController = mock(NavController::class.java)

        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        // Create a graphical FragmentScenario for the FirstScreen

        activityScenario.onActivity {
            val firstScenario = launchFragmentInContainer<SplashFragment>()
            firstScenario.onFragment { fragment ->
                Navigation.setViewNavController(fragment.requireView(), mockNavController)
            }
        }

        // Set the NavController property on the fragment


        onView(withId(R.id.appTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.appDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.appSite)).check(matches(isDisplayed()))
        onView(withId(R.id.animationView)).check(matches(isDisplayed()))
//            verify(mockNavController).navigate(R.id.action_splashFragment_to_settingFragment)
    }


    suspend fun getWeatherResponse(local: String): Resource<WeatherResponse> =
        Resource.Success(getWeatherResponse())


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

    fun getForecastResponse(): ForecastResponse {
        return ForecastResponse(

            city = City(
                coord = Coord(
                    lat = 4.2,
                    lon = 4.2
                ),
                country = "String?",
                id = 1,
                name = "String",
                population = 1,
                sunrise = 1,
                sunset = 1,
                timezone = 1
            ),
            cnt = 4,
            cod = "tring?",
            dayWeather = arrayListOf<DayWeather>(getDayWeather()),
            message = 2.0
        )
    }

    fun getDayWeather(): DayWeather {
        return DayWeather(
            main = Main(
                temp = 13.88,
                feelsLike = 11.66,
                tempMin = 11.85,
                tempMax = 13.88,
                pressure = 1011,
                humidity = 13,
            ),
            weather = arrayListOf<Weather>(
                Weather(
                    id = 803,
                    main = "Clouds",
                    description = "ابرهای پارچه پارچه شده",
                    icon = "04n"
                )
            ),
            clouds = Clouds(76),
            wind = Wind(
                speed = 8.73,
                deg = 291
            ),
            sys = Sys(
                country = "String",
                id = 1,
                sunrise = 1,
                sunset = 1,
                type = 1
            ),
            pop = 0.0,
            visibility = 10000,
            dt = 1646492400,
            dtTxt = "2022-03-05 15:00:00",


            )
    }


}