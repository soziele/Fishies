package com.example.fishies.api

import com.example.fishies.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UserApi {
    @GET
    suspend fun getUserState(
            @Url url: String
    ): Response<User>
}