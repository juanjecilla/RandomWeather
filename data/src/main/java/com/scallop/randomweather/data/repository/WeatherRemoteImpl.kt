package com.scallop.randomweather.data.repository

import com.scallop.randomweather.data.api.OpenWeatherApi
import com.scallop.randomweather.data.mappers.WeatherDataEntityMapper
import kotlinx.coroutines.flow.flow

class WeatherRemoteImpl constructor(
    private val mApi: OpenWeatherApi,
    private val mMapper: WeatherDataEntityMapper
) : RemoteDataSource {

    override suspend fun getWeatherByLocation(lat: Double, lon: Double) = flow {
        val result = mApi.getWeatherByLocation(lat, lon)
        emit(mMapper.mapToEntity(result))
    }
}