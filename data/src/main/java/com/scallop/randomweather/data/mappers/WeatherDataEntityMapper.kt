package com.scallop.randomweather.data.mappers

import com.scallop.randomweather.data.entitites.*
import com.scallop.randomweather.domain.entities.*

class WeatherDataEntityMapper {

    fun mapToEntity(data: WeatherData) = WeatherEntity(
        base = data.base,
        clouds = mapToEntity(data.clouds),
        cod = data.cod,
        coord = mapToEntity(data.coord),
        dt = data.dt,
        id = data.id,
        main = mapToEntity(data.main),
        name = data.name,
        rain = data.rain?.let { mapToEntity(it) },
        sys = mapToEntity(data.sys),
        timezone = data.timezone,
        visibility = data.visibility,
        weather = mapToEntity(data.weather),
        wind = mapToEntity(data.wind),
    )

    private fun mapToEntity(data: CloudsData) = CloudsEntity(
        all = data.all
    )

    private fun mapToEntity(data: CoordData) = CoordEntity(
        lat = data.lat,
        lon = data.lon
    )

    private fun mapToEntity(data: MainData) = MainEntity(
        feels_like = data.feels_like,
        grnd_level = data.grnd_level,
        humidity = data.humidity,
        pressure = data.pressure,
        sea_level = data.sea_level,
        temp = data.temp,
        temp_max = data.temp_max,
        temp_min = data.temp_min,


        )

    private fun mapToEntity(data: RainData) = RainEntity(
        `1h` = data.`1h`
    )

    private fun mapToEntity(data: SysData) = SysEntity(
        country = data.country,
        sunrise = data.sunrise,
        sunset = data.sunset,
    )

    private fun mapToEntity(data: List<WeatherItemData>) = data.map { mapToEntity(it) }

    private fun mapToEntity(data: WeatherItemData) = WeatherItemEntity(
        description = data.description,
        icon = data.icon,
        id = data.id,
        main = data.main,
    )

    private fun mapToEntity(data: WindData) = WindEntity(
        deg = data.deg,
        speed = data.speed,
    )
}
