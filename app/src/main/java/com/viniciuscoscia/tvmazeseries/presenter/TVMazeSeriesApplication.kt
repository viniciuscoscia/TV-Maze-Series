package com.viniciuscoscia.tvmazeseries.presenter

import android.app.Application
import com.viniciuscoscia.tvmazeseries.BuildConfig
import com.viniciuscoscia.tvmazeseries.data.di.dataModules
import com.viniciuscoscia.tvmazeseries.domain.di.domainModule
import com.viniciuscoscia.tvmazeseries.presenter.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class TVMazeSeriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
        startKoin {
            androidContext(this@TVMazeSeriesApplication)
            androidLogger(Level.ERROR)
            modules(presenterModule + dataModules + domainModule)
        }
    }
}