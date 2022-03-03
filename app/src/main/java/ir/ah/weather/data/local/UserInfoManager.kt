package ir.ah.weather.data.local
import android.content.*
import javax.inject.*


class UserInfoManager @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun saveUser(local: String) {
        val editor = sharedPreferences.edit()
        editor.putString("local", local)
        editor.apply()
    }



    fun getLocal(): String? {
        return sharedPreferences.getString("local", null)
    }


    fun clear() = sharedPreferences.edit().clear().apply()


}