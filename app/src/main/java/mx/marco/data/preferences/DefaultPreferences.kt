package mx.marco.data.preferences

import android.content.SharedPreferences
import mx.marco.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveShouldShowOnBoarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnBoarding(): Boolean {
        return sharedPref
            .getBoolean(Preferences.KEY_ONBOARDING, true)
    }


    override fun saveIsLogged(isLogged: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_IS_LOGGED, isLogged)
            .apply()
    }

    override fun loadIsLogged(): Boolean {
        return sharedPref
            .getBoolean(Preferences.KEY_IS_LOGGED, false)
    }

    override fun saveSessionToken(token: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_SESSION_TOKEN, token)
            .apply()
    }

    override fun loadSessionToken(): String {
        return sharedPref
            .getString(Preferences.KEY_SESSION_TOKEN, "") ?: ""
    }


}