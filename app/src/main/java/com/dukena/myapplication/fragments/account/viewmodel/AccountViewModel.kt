package com.dukena.myapplication.fragments.account.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dukena.myapplication.BuildConfig
import com.dukena.myapplication.activities.main.Model.MainInteractor
import com.dukena.myapplication.fragments.account.Model.AccountInteractor
import com.dukena.myapplication.models.request.TransactionRequest
import com.dukena.myapplication.models.response.ProfileResponse
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel(), AccountInteractor.AccountListener {
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val cardNumber = MutableLiveData<String>()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    fun getProfile() {
        _progressBar.value = true
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            var accountInteractor = AccountInteractor()
            accountInteractor.getProfile(this@AccountViewModel)
        }
    }

    fun getTransaccions() {
        _progressBar.value = true
        if( cardNumber.value.isNullOrEmpty()){
            _message.value  = "Introduzca un Numero de Tarjeta valido"
            return
        }
        val request = TransactionRequest(cardNumber.value!!.toLong(),BuildConfig.COUNTRY_CODE,true)
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            var accountInteractor = AccountInteractor()
            accountInteractor.getTransaction(request,this@AccountViewModel)
        }
    }

    override fun getResponse(response: Any?, exito: Boolean, messageResp: String) {
        _progressBar.value = false
        if (exito) {
            // exito en la base
                if(response !=null && response is ProfileResponse){
                    name.value = response!!.firstName + " " + response!!.lastName
                    email.value = response!!.email
                    cardNumber.value = response!!.cardNumber
                }
        } else {
            _message.value = messageResp
        }
    }
}