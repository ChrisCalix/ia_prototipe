package com.dukena.myapplication.fragments.movies.Model

import com.dukena.myapplication.BuildConfig
import com.dukena.myapplication.CinepolisService
import com.dukena.myapplication.RestEngine
import com.dukena.myapplication.fragments.account.Model.AccountInteractor
import com.dukena.myapplication.models.response.CinemaResponse
import com.dukena.myapplication.models.response.ProfileResponse
import com.dukena.myapplication.utils.BaseApplication
import com.dukena.myapplication.utils.Constants.Companion.NUM_CITY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInteractor {
    interface MovieListener {
        fun getResponse(
            response: Any?,
            exito: Boolean,
            message: String
        )
    }

    fun getCinemas(callback: MovieListener) {
        val cinepolisService = RestEngine.getInstance().create(CinepolisService::class.java)

        val result = cinepolisService.getCinemas(
            BuildConfig.API_KEY,
            NUM_CITY,
            BuildConfig.COUNTRY_CODE,
            true
        )
        result.enqueue(object : Callback<List<CinemaResponse>> {
            override fun onFailure(call: Call<List<CinemaResponse>>, t: Throwable) {
                // logginSuccess.value = false
                //     callback.getMessage("Favor intente mas tarde")
                callback.getResponse(null, false, "Favor intente mas tarde")
            }

            override fun onResponse(
                call: Call<List<CinemaResponse>>,
                response: Response<List<CinemaResponse>>
            ) {
                // callback.getProgress(false)
                when (response.code()) {
                    200 -> {
                        callback.getResponse(response.body(), true, "")

                        //callback.getProgress(true)
                        //   callback.getResponse( response.body())
                    }
                    else -> //callback.getMessage(
                        callback.getResponse(
                            null,
                            false,
                            "Favor intente mas tarde"
                            //response.body()?.error.toString() + " " + response.body()?.errorDesc.toString()
                        )
                }
            }
        })
    }
}