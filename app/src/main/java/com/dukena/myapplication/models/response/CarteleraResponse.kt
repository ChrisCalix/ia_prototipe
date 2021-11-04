package com.dukena.myapplication.models.response

import com.google.gson.annotations.SerializedName

data class CarteleraResponse(
    @SerializedName("error") val error: String,
    @SerializedName("error_descripcion") val errorDesc: String,
    val movies: List<Movies>,
    val routes: List<Routes>
)

data class Movies(
    val rating: String,
    val media: List<Media>,
    val cast: List<Cast>,
    val cinemas: List<Int>,
    val position: Int,
    val categories: List<String>,
    val genre: String,
    val synopsis: String,
    val length: String,
    val release_date: String,
    val distributor: String,
    val id: Int,
    val name: String,
    val code: String,
    val original_name: String
)

data class Media(
    val resource: String,
    val type: String,
    val code: String
)

data class Cast(
    val label: String,
    val value: List<String>
)

data class Routes(
    val code: String,
    val size: Sizes
)

data class Sizes(
    val large: String,
    val medium: String,
    val small: String
)