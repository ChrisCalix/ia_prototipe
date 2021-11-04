package com.dukena.myapplication

import android.content.Context
import com.dukena.myapplication.activities.main.view_model.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        @Volatile
        private var INSTANCE_CND:Retrofit? = null

        fun getInstance() = INSTANCE?: synchronized(Retrofit::class.java){
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            INSTANCE?: Retrofit.Builder().baseUrl("https://stage-api.cinepolis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().also { INSTANCE = it }
        }

        fun getRestEngine(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://stage-api.cinepolis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit
        }

        // BaseURL diferente para consumo de servicio
        fun getInstanceCDN() = INSTANCE_CND?: synchronized(Retrofit::class.java){
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            INSTANCE_CND?: Retrofit.Builder().baseUrl("https://cdn-api.cinepolis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().also { INSTANCE_CND = it }
        }
    }
}