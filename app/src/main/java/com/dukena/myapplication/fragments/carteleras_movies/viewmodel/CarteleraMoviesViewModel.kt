package com.dukena.myapplication.fragments.carteleras_movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dukena.myapplication.fragments.carteleras_movies.model.CarteleraMoviesInteractor
import com.dukena.myapplication.fragments.movies.Model.MovieInteractor
import com.dukena.myapplication.models.response.CarteleraResponse
import com.dukena.myapplication.models.response.CinemaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarteleraMoviesViewModel: ViewModel(), CarteleraMoviesInteractor.CartleraMoviesListener{

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _response = MutableLiveData<CarteleraResponse>()
    val response: LiveData<CarteleraResponse>
        get() = _response

    fun getMovies(){
       // _progressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val carteleraMovieInteractor = CarteleraMoviesInteractor()
            carteleraMovieInteractor.getCarteleraMovies(this@CarteleraMoviesViewModel)
        }
    }

    override fun getResponse(response: Any?, exito: Boolean, messageResp: String) {
        _progressBar.value = false
        if (exito) {
            // exito en la base
            if(response !=null && response is CarteleraResponse){
                _response.value = response as CarteleraResponse
            }
        } else {
            _message.value = messageResp
        }
    }
}