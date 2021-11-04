package com.dukena.myapplication.fragments.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dukena.myapplication.fragments.movies.Model.MovieInteractor
import com.dukena.myapplication.models.response.CinemaResponse
import com.dukena.myapplication.models.response.ProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel(), MovieInteractor.MovieListener {

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _response = MutableLiveData<List<CinemaResponse>>()
    val response: LiveData<List<CinemaResponse>>
        get() = _response

    fun getCinemas() {
        _progressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val movieInteractor = MovieInteractor()
            movieInteractor.getCinemas(this@MovieViewModel)
        }
    }

    override fun getResponse(response: Any?, exito: Boolean, messageResp: String) {
        _progressBar.value = false
        if (exito) {
            // exito en la base
            if(response !=null && response is List<*> && response.size > 0){
                _response.value = response as List<CinemaResponse>
            }
        } else {
            _message.value = messageResp
        }
    }
}