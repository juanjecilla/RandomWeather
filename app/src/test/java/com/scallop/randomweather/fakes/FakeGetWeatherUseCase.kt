package com.scallop.randomweather.fakes

import com.scallop.randomweather.domain.common.BaseUseCase
import com.scallop.randomweather.domain.entities.*
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.utils.TestUtils
import kotlinx.coroutines.flow.Flow

class FakeGetWeatherUseCase(
    status: Status
) : BaseTestUseCase<WeatherEntity, HashMap<String, Double>>(status),
    BaseUseCase<HashMap<String, Double>, Flow<WeatherEntity>> {

    override fun getValue(params: HashMap<String, Double>): WeatherEntity {
        return TestUtils.getWeatherEntity()
    }

    override suspend fun invoke(params: HashMap<String, Double>): Flow<WeatherEntity> =
        execute(params)

}