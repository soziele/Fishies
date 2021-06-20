package com.example.fishies.api

import com.example.fishies.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUserInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.USER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val userApi: UserApi by lazy {
        RetrofitUserInstance.retrofit.create(UserApi::class.java)
    }
}