package com.viniciuscoscia.tvmazeseries.presenter

import android.app.Application
import com.viniciuscoscia.tvmazeseries.data.di.dataModules
import com.viniciuscoscia.tvmazeseries.domain.di.domainModule
import com.viniciuscoscia.tvmazeseries.presenter.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TVMazeSeriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TVMazeSeriesApplication)
            modules(presenterModule + dataModules + domainModule)
        }
    }
}