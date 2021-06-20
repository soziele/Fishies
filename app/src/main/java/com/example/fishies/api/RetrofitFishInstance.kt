package com.example.fishies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fishies.utils.Constants.Companion.FISH_BASE_URL
import retrofit2.create

object RetrofitFishInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FISH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val fishApi: FishApi by lazy {
        retrofit.create(FishApi::class.java)
    }

}