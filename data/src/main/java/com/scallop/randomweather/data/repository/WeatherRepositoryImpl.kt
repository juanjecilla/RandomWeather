package com.scallop.randomweather.data.repository

import com.scallop.randomweather.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val mRemote: RemoteDataSource,
) : WeatherRepository {

    override suspend fun getWeatherByLocation(lat: Double, lon: Double) =
        mRemote.getWeatherByLocation(lat, lon)
}