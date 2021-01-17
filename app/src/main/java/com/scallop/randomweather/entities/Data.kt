package com.scallop.randomweather.entities

data class Data<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Error? = null
)

enum class Status { SUCCESSFUL, ERROR, LOADING }