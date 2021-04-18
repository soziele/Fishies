package com.example.fishies.repository

import com.example.fishies.api.RetrofitFishInstance
import com.example.fishies.model.FishData
import retrofit2.Response

class FishRepository {

    suspend fun getFish(name: String): Response<FishData> {
        return RetrofitFishInstance.api.getFish(name)
    }
}