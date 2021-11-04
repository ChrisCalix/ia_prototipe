package com.dukena.myapplication

import android.content.Context

class ApiService constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE :ApiService? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE ?: ApiService(context).also { INSTANCE = it }
        }
    }
}