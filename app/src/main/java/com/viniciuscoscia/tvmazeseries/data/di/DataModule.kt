package com.viniciuscoscia.tvmazeseries.data.di

import com.viniciuscoscia.tvmazeseries.BuildConfig
import com.viniciuscoscia.tvmazeseries.data.remote.api.TVMazeAPI
import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

private val dataModule = module {
    single<TVMazeRepository> {
        TVMazeRepositoryImpl(tvMazeApi = get())
    }
}

private val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }

    single {
        createWebService<TVMazeAPI>(
            okHttpClient = get(),
            url = BuildConfig.TVMazeBaseURL
        )
    }
}

val dataModules: List<Module> = listOf(remoteDataSourceModule, dataModule)