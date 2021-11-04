package com.dukena.myapplication.activities.main.Model

import com.dukena.myapplication.BuildConfig
import com.dukena.myapplication.CinepolisService
import com.dukena.myapplication.RestEngine
import com.dukena.myapplication.models.response.LoginResponse
import com.dukena.myapplication.utils.BaseApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainInteractor {
    interface MainInteractorCallback {
        fun getMessage(message : String)
        fun getProgress(boolean: Boolean)
        fun getResponse(loginResponse: LoginResponse?)
    }

    fun getAuthToken(user: String, password: String, callback: MainInteractorCallback) {
       // val cinepolisService: CinepolisService =
        //    RestEngine.getRestEngine().create(CinepolisService::class.java)
        val cinepolisService = RestEngine.getInstance().create(CinepolisService::class.java)
        val result = cinepolisService.getAuth2(
            BuildConfig.API_KEY,
            BuildConfig.COUNTRY_CODE,
            user,
            password,
            BuildConfig.GRANT_TYPE,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET
        )
        result.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // logginSuccess.value = false
                callback.getMessage("Favor intente mas tarde")
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                callback.getProgress(false)
                when (response.code()) {
                    200 -> {
                        response.body()?.let { BaseApplication.getInstance(it.accessToken, it.tokenType) }
                        //callback.getProgress(true)
                        callback.getResponse( response.body())
                    }
                    else -> callback.getMessage(
                        response.body()?.error.toString() + " " + response.body()?.errorDesc.toString())
                }
            }
        })
    }
}