package mx.com.satoritech.journeys.util.components

interface Preferences {
    suspend fun putUserValue(key: String, value: String)
    suspend fun getUserValue(key: String): String?
    suspend fun deleteAll()
}