package com.inspirecoding.wayairlines.main.application

import android.app.Application
import com.inspirecoding.wayairlines.di.data.dataModule
import com.inspirecoding.wayairlines.di.navigation.navigationModule
import com.inspirecoding.wayairlines.di.presentation.presentationModule
import com.inspirecoding.wayairlines.di.usecase.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    navigationModule,
                    presentationModule,
                    dataModule,
                    useCaseModule
                )
            )
        }
    }
}