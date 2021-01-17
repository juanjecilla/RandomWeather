package com.scallop.randomweather.data

import com.scallop.randomweather.data.api.OpenWeatherApi
import com.scallop.randomweather.data.fakes.OpenWeatherApiDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal open class BaseTest {


    lateinit var mApi: OpenWeatherApi
    val mMoshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private lateinit var mMockWebServer: MockWebServer
    private lateinit var mOkHttpClient: OkHttpClient
    private lateinit var mLoggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        mMockWebServer = MockWebServer()
        mMockWebServer.dispatcher = OpenWeatherApiDispatcher()
        mMockWebServer.start()
        mLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        mOkHttpClient = buildOkhttpClient(mLoggingInterceptor)

        mApi = Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .client(mOkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(mMoshi))
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @After
    open fun tearDown() {
        mMockWebServer.shutdown()
    }

    private fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}