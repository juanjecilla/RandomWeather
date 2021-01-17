package com.scallop.randomweather.entities

data class WeatherItem(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)