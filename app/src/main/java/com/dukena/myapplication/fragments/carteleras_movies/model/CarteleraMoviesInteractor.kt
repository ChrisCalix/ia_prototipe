package com.dukena.myapplication.fragments.carteleras_movies.model

import com.dukena.myapplication.BuildConfig
import com.dukena.myapplication.CinepolisService
import com.dukena.myapplication.RestEngine
import com.dukena.myapplication.fragments.account.Model.AccountInteractor
import com.dukena.myapplication.models.response.CarteleraResponse
import com.dukena.myapplication.models.response.ProfileResponse
import com.dukena.myapplication.utils.BaseApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarteleraMoviesInteractor {
    interface CartleraMoviesListener {
        fun getResponse(
            response: Any?,
            exito: Boolean,
            message: String
        )
    }

    fun getCarteleraMovies(callback: CarteleraMoviesInteractor.CartleraMoviesListener) {
        val cinepolisService = RestEngine.getInstance().create(CinepolisService::class.java)

        val result = cinepolisService.getMovies(
            BuildConfig.API_KEY_CDN,
            BuildConfig.COUNTRY_CODE,
            BuildConfig.CITIES.toInt()
        )
        result.enqueue(object : Callback<CarteleraResponse> {
            override fun onFailure(call: Call<CarteleraResponse>, t: Throwable) {
                // logginSuccess.value = false
                //     callback.getMessage("Favor intente mas tarde")
                callback.getResponse(null, false, "Favor intente mas tarde")
            }

            override fun onResponse(
                call: Call<CarteleraResponse>,
                response: Response<CarteleraResponse>
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
                            response.body()?.error.toString() + " " + response.body()?.errorDesc.toString()
                        )
                }
            }
        })
    }
}