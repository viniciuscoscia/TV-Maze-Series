package com.viniciuscoscia.tvmazeseries.domain.di

import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchEpisodeInfoUseCase
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowDetailsUseCase
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowEpisodeListUseCase
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowsByPageUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FetchShowsByPageUseCase(repository = get())
    }

    factory {
        FetchShowEpisodeListUseCase(repository = get())
    }

    factory {
        FetchShowDetailsUseCase(repository = get())
    }

    factory {
        FetchEpisodeInfoUseCase(repository = get())
    }
}