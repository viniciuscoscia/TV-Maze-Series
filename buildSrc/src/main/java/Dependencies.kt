object Versions {
    const val androidAppCompat = "1.4.1"
    const val androidMaterial = "1.6.0"
    const val androidConstraint = "2.0.4"
    const val activityCompose = "1.4.0"
    const val coil = "2.0.0-rc03"
    const val kotlin = "1.6.10"
    const val ktx = "1.7.0"
    const val gradlePlugin = "7.0.1"
    const val kotlinCoroutines = "1.6.1"
    const val lifecycle = "2.4.1"
    const val compose = "1.1.1"
    const val navigationCompose = "2.4.2"
    const val jUnit = "4.13.2"
    const val androidExtJUnit = "1.1.3"
    const val androidEspresso = "3.4.0"
    const val androidNavigation = "2.4.2"
    const val koin = "3.1.6"
    const val room = "2.4.2"
    const val timber = "5.0.1"
    const val multidex = "2.0.1"
    const val retrofit = "2.9.0"
    const val retrofitMoshi = "2.9.0"
    const val okHttpLog = "4.9.3"
    const val moshi = "1.13.0"
}

object Libs {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val androidConstraint =
        "androidx.constraintlayout:constraintlayout:${Versions.androidConstraint}"
    const val androidExtJUnit = "androidx.test.ext:junit:${Versions.androidExtJUnit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
    const val androidNavigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
    const val androidNavigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
    const val androidNavigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeFoundationLayout =
        "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha14"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    const val okhttpLog = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLog}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodegenKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Plugins {
    const val parcelize = "kotlin-parcelize"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val koin = "koin"
}

object ClassPaths {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val koinPlugin = "io.insert-koin:koin-gradle-plugin:${Versions.koin}"
    const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
}

const val latestSdk = 32
object Configs {
    const val applicationId = "com.viniciuscoscia.tvmazeseries"
    const val compileSdkVersion = latestSdk
    const val minSdkVersion = 21
    const val targetSdkVersion = latestSdk
    const val versionCode = 1
    const val versionName = "1.0.0"
}