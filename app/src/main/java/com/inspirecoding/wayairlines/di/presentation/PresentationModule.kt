package com.inspirecoding.wayairlines.di.presentation

import com.inspirecoding.wayairlines.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            getFlightsUseCase = get()
        )
    }
}