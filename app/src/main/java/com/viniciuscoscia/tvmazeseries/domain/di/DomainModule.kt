package com.viniciuscoscia.tvmazeseries.domain.di

import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowsByPageUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FetchShowsByPageUseCase(repository = get())
    }
}