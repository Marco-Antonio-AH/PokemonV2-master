plugins {
    id(Build.androidApplication)
    id(Kotlin.plugin)
    id(DaggerHilt.plugin)
    id(Kotlin.kaptPlugin)
    id(Kotlin.parcelize)
    //id(Firebase.plugin)
    //id(Firebase.pluginCrashlytics)
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.getVersionName()

        testInstrumentationRunner = Build.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${ProjectConfig.baseUrlDev}\"")
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            //buildConfigField("String", "BASE_URL", "\"${ProjectConfig.baseUrlProd}\"")
            //signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Kotlin.extensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)

    implementation(Compose.activityCompose)
    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.composeGraphics)
    implementation(Compose.composeToolingPreview)
    implementation(Compose.composeMaterial3)
    implementation(Compose.composeLifecycleViewModel)
    implementation(Compose.composeNavigation)
    implementation(Compose.composeLifecycle)
    implementation(Compose.composeHiltNavigation)
    debugImplementation(Compose.composeUiTooling)
    debugImplementation(Compose.composeTestManifest)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConverter)
    implementation(platform(Retrofit.okHttpBom))
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)

    //implementation(Maps.mapsCompose)
    //implementation(Maps.playServicesMaps)

    implementation(Coil.coilCompose)
    implementation ("io.coil-kt:coil-compose:2.0.0")
    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    //implementation(Location.playServicesLocation)
    //implementation(Location.placesApi)

    //implementation(platform(Firebase.firebaseBom))
    //implementation(Firebase.analytics)
    //implementation(Firebase.crashlytics)

    testImplementation(Testing.junit4)
    androidTestImplementation(Testing.extJunit)
    androidTestImplementation(Testing.espresso)
    androidTestImplementation(platform(Testing.composeBomTesting))
    androidTestImplementation(Testing.composeUiTesting)

    // For media playback using ExoPlayer
    implementation("androidx.media3:media3-exoplayer:1.0.0-rc01")
    implementation("androidx.media3:media3-ui:1.0.0-rc01")

    //COLOR STATUS BAR
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

}