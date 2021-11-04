package com.dukena.myapplication.utils

import com.dukena.myapplication.models.BaseModel
import com.dukena.myapplication.models.response.LoginResponse

class BaseApplication {
    companion object{
        @Volatile
        private var INSTANCE : BaseModel? = null

        fun getInstance(token: String, tokenType: String)= INSTANCE?: synchronized(BaseModel::class.java){
            INSTANCE?: BaseModel(token, tokenType) .also { INSTANCE  = it }
        }

        fun getInstance(): BaseModel {
            return INSTANCE!!
        }
    }
}