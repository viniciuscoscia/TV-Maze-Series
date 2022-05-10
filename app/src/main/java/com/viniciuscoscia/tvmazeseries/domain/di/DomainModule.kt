package com.viniciuscoscia.tvmazeseries.domain.di

import com.viniciuscoscia.tvmazeseries.domain.usecase.*
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

    factory {
        SearchShowByNameUseCase(repository = get())
    }
}