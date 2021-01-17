package com.scallop.randomweather.domain.usecases

import com.scallop.randomweather.domain.common.BaseUseCase
import com.scallop.randomweather.domain.entities.WeatherEntity
import com.scallop.randomweather.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase(
    private val mRepository: WeatherRepository
) : BaseUseCase<HashMap<String, Double>, Flow<WeatherEntity>> {

    override suspend fun invoke(params: HashMap<String, Double>) = mRepository.getWeatherByLocation(params["lat"]!!, params["lon"]!!)
}