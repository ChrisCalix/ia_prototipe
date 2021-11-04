package com.dukena.myapplication.models.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("error") val error: String,
    @SerializedName("error_descripcion") val errorDesc: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("profile_picture") val profilePicture: String,
    @SerializedName("card_number") val cardNumber: String
)

