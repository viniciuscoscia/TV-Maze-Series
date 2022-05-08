plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.navigationSafeArgs)
    id(Plugins.koin)
    id(Plugins.checkDependencyUpdates) version Versions.checkDependencyUpdates
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion

        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "TVMazeBaseURL", "\"https://api.tvmaze.com/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core
    implementation(Libs.kotlinStdLib)
    implementation(Libs.ktxCore)
    implementation(Libs.multidex)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.androidMaterial)

    // Jetpack LifeCycle
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleViewModel)

    // Koin
    implementation(Libs.koinAndroid)
    implementation(Libs.koinCompose)

    // Coroutine
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesAndroid)

    // Compose
    implementation(Libs.composeMaterial)
    implementation(Libs.composeUi)
    implementation(Libs.composeToolingPreview)
    implementation(Libs.composeFoundation)
    implementation(Libs.composeFoundationLayout)
    implementation(Libs.activityCompose)
    implementation(Libs.navigationCompose)
    implementation(Libs.pagingCompose)
    debugImplementation(Libs.composeTooling)

    // Coil
    implementation(Libs.coil)
    implementation(Libs.coilCompose)

    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)
    implementation(Libs.okhttpLog)

    // Moshi
    implementation(Libs.moshi)
    implementation(Libs.moshiKotlin)
    kapt(Libs.moshiCodegenKapt)

    // Log
    implementation(Libs.timber)

    // Test
    testImplementation(Libs.jUnit)
    testImplementation(Libs.coroutineTest)
    androidTestImplementation(Libs.androidExtJUnit)
    androidTestImplementation(Libs.androidEspresso)
}