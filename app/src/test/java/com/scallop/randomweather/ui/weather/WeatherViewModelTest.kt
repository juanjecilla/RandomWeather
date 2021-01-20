package com.scallop.randomweather.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.randomweather.CoroutineTestRule
import com.scallop.randomweather.entities.Data
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.entities.Weather
import com.scallop.randomweather.fakes.FakeGetWeatherUseCase
import com.scallop.randomweather.mappers.WeatherMapper
import com.scallop.randomweather.utils.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class WeatherViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mViewModel: WeatherViewModel
    private lateinit var mMapper: WeatherMapper

    @Mock
    lateinit var mObserver: Observer<Data<Weather>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mViewModel = WeatherViewModel(FakeGetWeatherUseCase(Status.SUCCESSFUL), WeatherMapper())
        mViewModel.data.observeForever(mObserver)
        mMapper = WeatherMapper()
    }

    @Test
    fun `getting weather expecting successful results`() {
        // No need to call get weather method because it is call on viewModel's init
        verify(mObserver).onChanged(Data(Status.LOADING))
        verify(mObserver).onChanged(
            Data(
                Status.SUCCESSFUL, mMapper.mapTo(TestUtils.getWeatherEntity())
            )
        )
    }
}