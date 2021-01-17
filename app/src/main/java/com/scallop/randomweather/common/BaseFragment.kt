package com.scallop.randomweather.common

import androidx.fragment.app.Fragment
import com.scallop.randomweather.databinding.ProgressBarBinding
import com.scallop.randomweather.ui.commons.visible

open class BaseFragment : Fragment() {

    fun showProgressBar(binding: ProgressBarBinding, visible: Boolean) {
        binding.root.visible(visible)
    }
}
