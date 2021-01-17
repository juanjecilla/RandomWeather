package com.scallop.randomweather.data.fakes

import com.scallop.randomweather.data.utils.getJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

/**
 * Handles Requests from mock web server
 */
internal class OpenWeatherApiDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/data/2.5/weather?appid=0bc9bc2a73fd9644f664cf5f5c5be8d7&lat=32.1&lon=32.1" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/weather.json"))
            }
            else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }
}