package com.viniciuscoscia.tvmazeseries.presenter.di

import com.viniciuscoscia.tvmazeseries.presenter.ui.main.MainViewModel
import com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails.TVShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel {
        MainViewModel()
    }

    viewModel {
        TVShowDetailsViewModel()
    }
}