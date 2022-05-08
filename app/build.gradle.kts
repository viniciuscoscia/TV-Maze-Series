import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.navigationSafeArgs)
    id(Plugins.koin)
    id("name.remal.check-dependency-updates") version "1.5.0"
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
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
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
    implementation(Libs.lifecycleLiveData)

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
    implementation(Libs.activityCompose)
    debugImplementation(Libs.composeTooling)

    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
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