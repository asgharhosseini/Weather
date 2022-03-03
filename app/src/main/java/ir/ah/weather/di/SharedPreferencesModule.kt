package ir.ah.weather.di
import android.content.*
import android.content.Context.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import ir.ah.weather.data.local.UserInfoManager
import ir.ah.weather.other.util.Constance.SHARED_PREFERENCES_NAME

import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideUserInfo(sharedPreferences: SharedPreferences) = UserInfoManager(sharedPreferences)

}