package com.scallop.randomweather.mappers

import com.scallop.randomweather.domain.entities.*
import com.scallop.randomweather.entities.*

class WeatherMapper {

    fun mapTo(entity: WeatherEntity) = Weather(
        base = entity.base,
        clouds = mapTo(entity.clouds),
        cod = entity.cod,
        coord = mapTo(entity.coord),
        dt = entity.dt,
        id = entity.id,
        main = mapTo(entity.main),
        name = entity.name,
        rain = entity.rain?.let { mapTo(it) },
        sys = mapTo(entity.sys),
        timezone = entity.timezone,
        visibility = entity.visibility,
        weather = mapTo(entity.weather),
        wind = mapTo(entity.wind),
    )

    private fun mapTo(entity: CloudsEntity) = Clouds(
        all = entity.all
    )

    private fun mapTo(entity: CoordEntity) = Coord(
        lat = entity.lat,
        lon = entity.lon
    )

    private fun mapTo(entity: MainEntity) = Main(
        feels_like = entity.feels_like,
        grnd_level = entity.grnd_level,
        humidity = entity.humidity,
        pressure = entity.pressure,
        sea_level = entity.sea_level,
        temp = entity.temp,
        temp_max = entity.temp_max,
        temp_min = entity.temp_min,
    )

    private fun mapTo(entity: RainEntity) = Rain(
        `1h` = entity.`1h`
    )

    private fun mapTo(entity: SysEntity) = Sys(
        country = entity.country,
        sunrise = entity.sunrise,
        sunset = entity.sunset,
    )

    private fun mapTo(entity: List<WeatherItemEntity>) = entity.map { mapTo(it) }

    private fun mapTo(entity: WeatherItemEntity) = WeatherItem(
        description = entity.description,
        icon = entity.icon,
        id = entity.id,
        main = entity.main,
    )

    private fun mapTo(entity: WindEntity) = Wind(
        deg = entity.deg,
        speed = entity.speed,
    )
}
