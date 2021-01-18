package com.scallop.randomweather.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.scallop.randomweather.CoroutineTestRule
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.fakes.FakeGetWeatherUseCase
import com.scallop.randomweather.mappers.WeatherMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mViewModel: WeatherViewModel

    @Before
    fun setup() {
        mViewModel = WeatherViewModel(FakeGetWeatherUseCase(Status.SUCCESSFUL), WeatherMapper())
    }

    @Test
    fun `requesting weather and expection successful result`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            mViewModel.getWeather()

            mViewModel.data.observeForever {
                Truth.assertThat(it.responseType).isEqualTo(Status.SUCCESSFUL)
                Truth.assertThat(it.data).isNotNull()
            }
        }
    }
}