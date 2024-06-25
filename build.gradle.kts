// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Build.androidApplication) version Build.androidApplicationVersion apply false
    id(Kotlin.plugin) version Kotlin.version apply false
    id(DaggerHilt.plugin) version DaggerHilt.version apply false
    //id(Firebase.plugin) version Firebase.pluginVersion apply false
    //id(Firebase.pluginCrashlytics) version Firebase.pluginCrashlyticsVersion apply false
}