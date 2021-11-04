package com.dukena.myapplication.activities.main.view_model

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dukena.myapplication.CinepolisService
import com.dukena.myapplication.RestEngine
import com.dukena.myapplication.activities.main.Model.MainInteractor
import com.dukena.myapplication.models.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel : ViewModel() , MainInteractor.MainInteractorCallback {

    val user = MutableLiveData<String>()
    //private val _user = MutableLiveData<String>()
    //val user: LiveData<String>
    //    get() = _user

    val password = MutableLiveData<String>()

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse>
        get() = _loginResponse

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message


    fun callGetAuth() {
        _progressBar.value = true
        if (password.value.isNullOrEmpty()) {
            _message.value = "Introduzca un password valido"
            return
        }
        if (user.value.isNullOrEmpty()) {
            _message.value = "Introduzca un usuario valido"
            return
        }


        //val result: Call<LoginRequest> = cinepolisService.getAuth("stage_HNYh3RaK_Test",loginRequest)
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            var mainInteractor = MainInteractor()
            mainInteractor.getAuthToken(user.value.toString() ,password.value.toString(),this@MainViewModel)
        }
    }

    override fun getMessage(message: String) {
        _message.value = message
    }

    override fun getProgress(boolean: Boolean) {
        _progressBar.value = boolean
    }

    override fun getResponse(loginResponse: LoginResponse?) {
        _loginResponse.value = loginResponse
        user.value = ""
        password.value = ""
    }
}
