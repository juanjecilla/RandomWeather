/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.scallop.randomweather.fakes

import com.scallop.randomweather.domain.common.BaseUseCase
import com.scallop.randomweather.domain.entities.*
import com.scallop.randomweather.entities.Status
import kotlinx.coroutines.flow.Flow

class FakeGetWeatherUseCase(
    status: Status
) : BaseTestUseCase<WeatherEntity, HashMap<String, Double>>(status),
    BaseUseCase<HashMap<String, Double>, Flow<WeatherEntity>> {

    override fun getValue(params: HashMap<String, Double>): WeatherEntity {
        return WeatherEntity(
            "",
            CloudsEntity(0),
            0,
            CoordEntity(0.0, 0.0),
            0,
            0,
            MainEntity(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0),
            "",
            null,
            SysEntity("", 0, 0),
            0,
            0,
            emptyList(),
            WindEntity(0, 0.0)
        )
    }

    override suspend fun invoke(params: HashMap<String, Double>): Flow<WeatherEntity> =
        execute(params)

}