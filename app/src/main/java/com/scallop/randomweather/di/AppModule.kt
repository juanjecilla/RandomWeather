package com.scallop.randomweather.di

import com.scallop.randomweather.data.api.OpenWeatherApi
import com.scallop.randomweather.data.mappers.WeatherDataEntityMapper
import com.scallop.randomweather.data.repository.RemoteDataSource
import com.scallop.randomweather.data.repository.WeatherRemoteImpl
import com.scallop.randomweather.data.repository.WeatherRepositoryImpl
import com.scallop.randomweather.domain.repositories.WeatherRepository
import com.scallop.randomweather.domain.usecases.GetWeatherUseCase
import com.scallop.randomweather.mappers.WeatherMapper
import com.scallop.randomweather.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@Suppress("USELESS_CAST") // It is important to maintain the dependency tree
val mRepositoryModules = module {
    single { WeatherRemoteImpl(get(), WeatherDataEntityMapper()) as RemoteDataSource }
    single {
        WeatherRepositoryImpl(
            get()
        ) as WeatherRepository
    }
}

val mUseCaseModules = module {
    factory { GetWeatherUseCase(get()) }
}

val mNetworkModules = module {
    single { createNetworkClient(BASE_URL, get()) }
    single { (get() as Retrofit).create(OpenWeatherApi::class.java) }
}

val mViewModels = module {
    viewModel { WeatherViewModel(get(), WeatherMapper()) }
}


private const val BASE_URL = "https://api.openweathermap.org/"
