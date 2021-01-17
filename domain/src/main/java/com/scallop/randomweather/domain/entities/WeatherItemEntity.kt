package com.scallop.randomweather.domain.entities

data class WeatherItemEntity(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)