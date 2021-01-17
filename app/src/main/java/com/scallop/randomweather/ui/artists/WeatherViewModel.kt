package com.scallop.randomweather.ui.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.randomweather.domain.usecases.GetWeatherUseCase
import com.scallop.randomweather.entities.Data
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.entities.Weather
import com.scallop.randomweather.mappers.WeatherMapper
import com.scallop.randomweather.ui.commons.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val mUseCase: GetWeatherUseCase,
    private val mMapper: WeatherMapper
) : ViewModel() {

    private val _data = MutableLiveData<Data<Weather>>()
    val data: LiveData<Data<Weather>> get() = _data

    init {
        getWeather()
    }

    fun getWeather() {
        _data.value = Data(Status.LOADING)
        val params = HashMap<String, Double>()
        params["lat"] = Utils.getRandomLatitude()
        params["lon"] = Utils.getRandomLongitude()

        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase(params)
            }
            results.map {
                _data.value = Data(Status.SUCCESSFUL, mMapper.mapTo(it))
            }.collect()
        }
    }
}