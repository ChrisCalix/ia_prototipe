package com.dukena.myapplication.models.response

import com.google.gson.annotations.SerializedName

data class CinemaResponse(
    @SerializedName("error") val error: String,
    @SerializedName("error_descripcion") val errorDesc: String,
    val id: Int,
    val vista_id: String,
    val uris: String,
    val city_id: Int,
    val name: String,
    val lat: String,
    val lng: String,
    val phone: String,
    val address: String,
    val position: Int,
    val setting: Setting

)
data class Setting(
    val is_special_prices: Boolean,
    val type_food_sales: String,
    val cs_merchant_id: String,
    val vco_merchant_id: String
)

