package com.scallop.randomweather.domain.common

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}