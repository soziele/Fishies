package com.example.fishies.api

import com.example.fishies.model.FishData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface FishApi {
    @GET
    suspend fun getFish(
        @Url url: String
    ): Response<FishData>
}