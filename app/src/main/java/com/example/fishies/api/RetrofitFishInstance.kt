package com.example.fishies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fishies.utils.Constants.Companion.BASE_URL

object RetrofitFishInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: FishApi by lazy {
        retrofit.create(FishApi::class.java)
    }
}