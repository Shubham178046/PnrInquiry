package com.example.pnrinquiry.ApiClient

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class Client {

    companion object{
        val okHttpClient =OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(240,TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        fun getService() : ApiInterface {
            val retrofit = Retrofit.Builder().baseUrl(AppConstants.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}