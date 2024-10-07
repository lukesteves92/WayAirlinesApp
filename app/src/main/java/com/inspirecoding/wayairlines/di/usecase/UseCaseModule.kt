package com.inspirecoding.wayairlines.di.usecase

import com.inspirecoding.wayairlines.domain.usecase.GetFlightsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetFlightsUseCase(repository = get())
    }
}