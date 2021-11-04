package com.dukena.myapplication.fragments.account.Model

import androidx.lifecycle.MutableLiveData
import com.dukena.myapplication.BuildConfig
import com.dukena.myapplication.CinepolisService
import com.dukena.myapplication.RestEngine
import com.dukena.myapplication.models.request.TransactionRequest
import com.dukena.myapplication.models.response.LoginResponse
import com.dukena.myapplication.models.response.ProfileResponse
import com.dukena.myapplication.models.response.TransactionResponse
import com.dukena.myapplication.utils.BaseApplication
import com.dukena.myapplication.utils.Constants.Companion.CONTENT_TYPE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInteractor {

    interface AccountListener {
        fun getResponse(
            response: Any?,
            exito: Boolean,
            message: String
        )
    }

    fun getProfile(callback: AccountListener) {
        val cinepolisService = RestEngine.getInstance().create(CinepolisService::class.java)

        val result = cinepolisService.getProfile(
            BuildConfig.API_KEY,
            BaseApplication.getInstance().tokenType + " " + BaseApplication.getInstance().token,
            BuildConfig.COUNTRY_CODE
        )
        result.enqueue(object : Callback<ProfileResponse> {
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                // logginSuccess.value = false
                //     callback.getMessage("Favor intente mas tarde")
                callback.getResponse(null, false, "Favor intente mas tarde")
            }

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
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

    fun getTransaction(request: TransactionRequest, callback: AccountListener) {
        val cinepolisService = RestEngine.getInstance().create(CinepolisService::class.java)

        val result = cinepolisService.getTransaction(
            BuildConfig.API_KEY,
            BaseApplication.getInstance().tokenType + " " + BaseApplication.getInstance().token,
            CONTENT_TYPE,
            request
        )
        result.enqueue(object : Callback<TransactionResponse> {
            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                // logginSuccess.value = false
                //     callback.getMessage("Favor intente mas tarde")
                callback.getResponse(null, false, "Favor intente mas tarde")
            }

            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>
            ) {
                // callback.getProgress(false)
                when (response.code()) {
                    200 -> {
                        callback.getResponse(null, true, "")
                        //callback.getProgress(true)
                        //   callback.getResponse( response.body())
                    }
                    else -> //callback.getMessage(
                        callback.getResponse(
                            null,
                            false,
                            response.body()?.status_code.toString() + " " + response.body()?.mesasge.toString()
                        )
                }
            }
        })
    }
}