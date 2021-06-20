package com.example.fishies.repository

import com.example.fishies.api.RetrofitFishInstance
import com.example.fishies.model.User
import retrofit2.Response

class UserRepository {
    suspend fun getUserState(url: String): Response<User> {
        return RetrofitFishInstance.userApi.getUserState(url)
    }
}