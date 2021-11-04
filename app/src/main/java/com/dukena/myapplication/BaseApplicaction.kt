package com.dukena.myapplication

import android.app.Application

class BaseApplicaction : Application() {
    companion object {
        lateinit var apiService: ApiService
    }

    override fun onCreate() {
        super.onCreate()
        apiService = ApiService.getInstance(this)
    }
}