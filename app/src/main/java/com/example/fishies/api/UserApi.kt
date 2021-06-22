package com.example.fishies.api

import com.example.fishies.model.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @Headers("API_KEY: 0e302bdaae60ec77e3bf9dd17e505c0d06d77276b383dad75195c4d8cd67c12853394d55326e5538788fa2426a37f483")
    @GET("user/{id}")
    suspend fun getUserState(
        @Path("id") id: String
    ): Response<User>

    @PUT
    suspend fun updateUserState(
        @Url url: String,
        @Body body: RequestBody
    )
}