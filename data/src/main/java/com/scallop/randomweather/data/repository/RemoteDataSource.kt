package com.scallop.randomweather.data.repository

import com.scallop.randomweather.data.entitites.WeatherData
import com.scallop.randomweather.domain.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): Flow<WeatherEntity>
}