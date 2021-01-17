package com.scallop.randomweather.domain.repositories

import com.scallop.randomweather.domain.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): Flow<WeatherEntity>
}