package com.scallop.randomweather.data.repository

import com.google.common.truth.Truth
import com.scallop.randomweather.data.BaseTest
import com.scallop.randomweather.data.mappers.WeatherDataEntityMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.EOFException


internal class WeatherRemoteImplTest : BaseTest() {

    private lateinit var mRemote: WeatherRemoteImpl
    private val mMapper = WeatherDataEntityMapper()

    @Before
    override fun setup() {
        super.setup()
        mRemote = WeatherRemoteImpl(mApi, mMapper)
    }

    @Test
    fun `get weather by location with successful results`() {
        runBlocking {
            val results = mRemote.getWeatherByLocation(32.1, 32.1)
            results.collect {
                Truth.assertThat(it).isNotNull()
            }
        }
    }
    @Test(expected = EOFException::class)
    fun `get failed weather with exception expected`() {
        runBlocking {
            val results = mRemote.getWeatherByLocation(0.0, 0.0)
            results.collect {
                Truth.assertThat(it).isNull()
            }
        }
    }
}