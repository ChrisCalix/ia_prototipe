package com.dukena.myapplication.models.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error") val error: String,
    @SerializedName("error_descripcion") val errorDesc: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("username") val username: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName(".issued") val issued: String,
    @SerializedName(".expires") val expires: String
)