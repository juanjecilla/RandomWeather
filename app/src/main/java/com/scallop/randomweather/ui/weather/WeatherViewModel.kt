package com.scallop.randomweather.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.randomweather.domain.common.BaseUseCase
import com.scallop.randomweather.domain.entities.WeatherEntity
import com.scallop.randomweather.entities.Data
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.entities.Weather
import com.scallop.randomweather.mappers.WeatherMapper
import com.scallop.randomweather.ui.commons.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val mUseCase: BaseUseCase<HashMap<String, Double>, Flow<WeatherEntity>>,
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

        try {
            viewModelScope.launch {
                val results = withContext(Dispatchers.IO) {
                    mUseCase(params)
                }
                results.map {
                    _data.value = Data(Status.SUCCESSFUL, mMapper.mapTo(it))
                }.collect()
            }
        } catch (e: Exception) {
            _data.value = Data(Status.ERROR)
        }
    }
}