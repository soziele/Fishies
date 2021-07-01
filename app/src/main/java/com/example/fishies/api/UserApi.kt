package com.example.fishies.api

import com.example.fishies.model.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @GET("user/{id}")
    suspend fun getUserState(
            @Header("API_KEY") ApiKey: String = "0e302bdaae60ec77e3bf9dd17e505c0d06d77276b383dad75195c4d8cd67c12853394d55326e5538788fa2426a37f483",
        @Path("id") id: String
    ): Response<User>


    @PATCH("user/{id}")
    suspend fun updateUserState(
            @Header("API_KEY") ApiKey: String = "0e302bdaae60ec77e3bf9dd17e505c0d06d77276b383dad75195c4d8cd67c12853394d55326e5538788fa2426a37f483",
            @Path("id") id: String,
        @Body body: RequestBody
    )

    @DELETE("user/{id}")
    suspend fun deleteUserState(
            @Header("API_KEY") ApiKey: String = "0e302bdaae60ec77e3bf9dd17e505c0d06d77276b383dad75195c4d8cd67c12853394d55326e5538788fa2426a37f483",
            @Path("id") id: String
    )
}