package com.scallop.randomweather.data.entitites

data class WeatherData(
    val base: String,
    val clouds: CloudsData,
    val cod: Int,
    val coord: CoordData,
    val dt: Int,
    val id: Int,
    val main: MainData,
    val name: String,
    val rain: RainData?,
    val sys: SysData,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherItemData>,
    val wind: WindData
)