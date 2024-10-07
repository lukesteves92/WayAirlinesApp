package com.inspirecoding.wayairlines.di.data

import com.inspirecoding.wayairlines.BuildConfig
import com.inspirecoding.wayairlines.data.remote.services.retrofit.RetrofitConfig
import com.inspirecoding.wayairlines.data.remote.services.service.WayAirlinesService
import com.inspirecoding.wayairlines.data.remote.services.wrapper.RequestWrapper
import com.inspirecoding.wayairlines.data.remote.services.wrapper.RequestWrapperImpl
import com.inspirecoding.wayairlines.data.repository.FlightsRepositoryImpl
import com.inspirecoding.wayairlines.domain.repository.FlightsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }

    single {
        RetrofitConfig.provideOkHttpClient()
    }

    single<RequestWrapper> {
        RequestWrapperImpl()
    }

    single<WayAirlinesService> {
        RetrofitConfig.createService(get(), BuildConfig.URL_BASE)
    }

    factory<FlightsRepository> {
        FlightsRepositoryImpl(
            wrapper = get(),
            service = get()
        )
    }
}