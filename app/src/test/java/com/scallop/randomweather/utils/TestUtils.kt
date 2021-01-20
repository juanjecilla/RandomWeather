package com.scallop.randomweather.utils

import com.scallop.randomweather.domain.entities.*

object TestUtils {

    fun getWeatherEntity() = WeatherEntity(
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