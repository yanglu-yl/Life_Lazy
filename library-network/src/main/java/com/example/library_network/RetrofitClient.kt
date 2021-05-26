package com.example.library_network

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object{
        fun getInstance() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Log.WARN)
                .build())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun sendHttpRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    fun <T> create(service: Class<T>?): T =
        sendHttpRetrofit().create(service!!) ?: throw RuntimeException("Api service is null!")

}