package com.scallop.randomweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.scallop.randomweather.R
import com.scallop.randomweather.common.BaseFragment
import com.scallop.randomweather.databinding.FragmentWeatherBinding
import com.scallop.randomweather.entities.Status
import com.scallop.randomweather.entities.Weather
import com.scallop.randomweather.ui.commons.Utils
import com.scallop.randomweather.ui.commons.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    private val mViewModel: WeatherViewModel by viewModel()

    private var mBinding: FragmentWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentWeatherBinding.bind(view)

        mBinding?.let {
            with(it) {
                next.setOnClickListener { mViewModel.getWeather() }
            }
        }

        mViewModel.data.observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.LOADING -> {
                    mBinding?.weatherInfoLayout?.visible(false)
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.weatherInfoLayout?.visible(true)
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }

                    it.data?.let { it1 ->
                        updateUI(it1)
                    }
                }
                Status.ERROR -> {
                    mBinding?.weatherInfoLayout?.visible(true)
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    mBinding?.root?.let { it1 ->
                        Snackbar.make(
                            it1,
                            R.string.error_weather,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun updateUI(weather: Weather) {
        mBinding?.let {
            with(it) {
                if (weather.weather.isNotEmpty()) {
                    val icon = weather.weather[0].icon
                    weatherIcon.load("https://openweathermap.org/img/wn/${icon}@2x.png")
                }
                cityValue.text = if (weather.name.isNotEmpty()) {
                    weather.name
                } else {
                    context?.getString(R.string.name_not_found)
                }
                latlonValue.text = context?.getString(R.string.latlon_placeholder)
                    ?.format(weather.coord.lat.toString(), weather.coord.lon.toString())
                temperatureValue.text = context?.getString(R.string.temperature_placeholder)
                    ?.format(Utils.getCelsiusFromKelvin(weather.main.temp))
                humidityValue.text = context?.getString(R.string.humidity_placeholder)
                    ?.format(weather.main.humidity)
            }
        }
    }
}