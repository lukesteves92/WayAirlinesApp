package com.inspirecoding.wayairlines.di.navigation

import com.inspirecoding.wayairlines.navigation.manager.NavigationManager
import org.koin.dsl.module

val navigationModule = module {
    single {
        NavigationManager(get())
    }
}