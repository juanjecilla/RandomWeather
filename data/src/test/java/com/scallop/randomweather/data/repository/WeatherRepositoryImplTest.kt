package com.scallop.randomweather.data.repository

import com.google.common.truth.Truth
import com.scallop.randomweather.data.BaseTest
import com.scallop.randomweather.data.mappers.WeatherDataEntityMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class WeatherRepositoryImplTest : BaseTest() {

    private lateinit var mRepository: WeatherRepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        mRepository = WeatherRepositoryImpl(WeatherRemoteImpl(mApi, WeatherDataEntityMapper()))
    }

    @Test
    fun `get weather by location wasith successful results`() {
        runBlocking {
            val results = mRepository.getWeatherByLocation(32.1, 32.1)
            results.collect {
                Truth.assertThat(it).isNotNull()
            }
        }
    }
}