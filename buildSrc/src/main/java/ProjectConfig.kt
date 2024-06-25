object ProjectConfig {
    const val appId = "mx.marco_ah"
    const val compileSdk = 34
    const val minSdk = 26
    const val targetSdk = 34

    const val versionCode = 1
    private const val versionMajor = 1
    private const val versionMinor = 2
    private const val versionPatch = 0

    fun getVersionName(): String {
        return "$versionMajor.$versionMinor.$versionPatch"
    }

    //BuildConfig create base url
    const val baseUrlDev = "https://pokeapi.co/api/v2/"
    const val baseUrlProd = "https://pokeapi.co/api/v2/"

    //signing config for release
    /*
    const val storeFile = "../keys/satori_2020.jks"
    const val storePassword = "123456"
    const val keyAlias = "satoritech"
    const val keyPassword = "123456"*/
}