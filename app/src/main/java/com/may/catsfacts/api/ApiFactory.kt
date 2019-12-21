package com.may.catsfacts.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory {

    companion object {
        private const val TYPE_CLIENT_IMAGE = 0
        private const val CONNECTION_TIMEOUT_SEC = 10L
        private const val BASE_URL_OF_IMAGE="https://aws.random.cat/"
        private const val BASE_URL_OF_STATEMENT="https://cat-fact.herokuapp.com/"
    }

    fun createApiClient(type: Int) : IApiClient {
        return if (type==TYPE_CLIENT_IMAGE) createRetrofit(BASE_URL_OF_IMAGE).create(IApiClient::class.java)
        else createRetrofit(BASE_URL_OF_STATEMENT).create(IApiClient::class.java)
    }

    private val gson = Gson()

    private fun createRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(createHttpClient())
            .build()
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(loggingInterceptor)

        builder.connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        builder.readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
        builder.writeTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)

        return builder.build()
    }
}