package com.dukena.myapplication.models.response

 open class BaseResponse(
     protected val error: String,
     protected val error_description: String
 )