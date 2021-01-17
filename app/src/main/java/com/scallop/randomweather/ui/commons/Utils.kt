package com.scallop.randomweather.ui.commons

import kotlin.random.Random

object Utils {

    fun getRandomLatitude() = Random.nextDouble(-90.0000, 90.0000)

    fun getRandomLongitude() = Random.nextDouble(-180.0000, 180.0000)
}