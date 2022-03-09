package ir.ah.weather.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import ir.ah.weather.ui.MainCoroutineRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest


import org.junit.After
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
class UserInfoManagerImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    lateinit var userInfoManager:UserInfoManagerImpl
    @Mock
    lateinit var userPreferencesDataStore: DataStore<Preferences>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this);
        userInfoManager=UserInfoManagerImpl(userPreferencesDataStore)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun saveUser()= runBlockingTest {
        val localName="tehran"
        userInfoManager.saveUser(localName)

        assertThat(localName).isEqualTo(userInfoManager.getLocal())

    }

    @Test
    fun getLocal()= runBlockingTest {
        val localName="tehran"
        userInfoManager.saveUser(localName)
        assertThat(localName).isEqualTo(userInfoManager.getLocal())

    }
}