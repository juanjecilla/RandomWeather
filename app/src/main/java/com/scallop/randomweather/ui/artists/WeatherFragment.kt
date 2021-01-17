package com.scallop.randomweather.ui.artists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scallop.randomweather.R
import com.scallop.randomweather.common.BaseFragment
import com.scallop.randomweather.databinding.FragmentWeatherBinding
import com.scallop.randomweather.entities.Status
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
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, true) }
                }
                Status.SUCCESSFUL -> {
                    mBinding?.progressBar?.let { it1 -> showProgressBar(it1, false) }
                    it.data?.let { it1 ->
                        Log.d("HOLA", it1.toString())
                    }
                }
                Status.ERROR -> {
                    Log.d("", "")
                }
            }
        })
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}