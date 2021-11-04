package com.dukena.myapplication

import com.dukena.myapplication.models.request.LoginRequest
import com.dukena.myapplication.models.request.TransactionRequest
import com.dukena.myapplication.models.response.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface CinepolisService {
    @POST("v2/oauth/token")
    fun getAuth(
        @Header("api_key") api: String,
        @Body loginRequest: LoginRequest
    ): Call<LoginRequest>

    //@Headers("api_key: 199e2ce46ac525fddf")
    @FormUrlEncoded
    @POST("v2/oauth/token")
    fun getAuth2(
        @Header("api_key") apiKey: String,
        @Field("country_code") countryCode: String,
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Call<LoginResponse>

    @GET("v1/members/profile")
    fun getProfile(
        @Header("api_key") api: String,
        @Header("Authorization") token: String,
        @Query("country_code") countryCode: String
    ): Call<ProfileResponse>

    @POST("v1/members/loyalty")
    fun getTransaction(
        @Header("api_key") api: String,
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType : String,
        @Body request: TransactionRequest
    ): Call<TransactionResponse>

    @GET("v2/locations/cinemas")
    fun getCinemas(
        @Header("api_key") api:String,
        @Query("cities") cities: Int,
        @Query("country_code") countryCode: String,
        @Query("include_cinemas") includeCinema:Boolean
    ): Call<List<CinemaResponse>>

    @GET("v2/movies")
    fun getMovies(
        @Header("api_key") api: String,
        @Query("country_code") countryCode: String,
        @Query("cities") cities: Int
    ) : Call<CarteleraResponse>
}