package com.scallop.randomweather.fakes

import com.scallop.randomweather.entities.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseTestUseCase<out T, in P>(private val status: Status) {

    fun execute(params: P): Flow<T> = flow {
        when (status) {
            Status.SUCCESSFUL -> emit(getValue(params))
            Status.ERROR -> throw Exception("Something went wrong")
            else -> throw Exception("Something went wrong")
        }
    }

    abstract fun getValue(params: P): T

}