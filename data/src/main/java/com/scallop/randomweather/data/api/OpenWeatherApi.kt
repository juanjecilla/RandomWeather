package com.scallop.randomweather.data.api

import com.scallop.randomweather.data.entitites.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/weather?appid=0bc9bc2a73fd9644f664cf5f5c5be8d7")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): WeatherData
}