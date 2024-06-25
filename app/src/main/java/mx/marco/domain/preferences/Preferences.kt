package mx.marco.domain.preferences


interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean
    fun saveIsLogged(isLogged: Boolean)
    fun loadIsLogged(): Boolean
    fun saveSessionToken(token: String)
    fun loadSessionToken(): String

    companion object {
        const val KEY_ONBOARDING = "key_onboarding"
        const val KEY_USER_LOCATION = "key_user_location"
        const val KEY_IS_LOGGED = "key_is_logged"
        const val KEY_SESSION_TOKEN = "key_session_token"
    }


}