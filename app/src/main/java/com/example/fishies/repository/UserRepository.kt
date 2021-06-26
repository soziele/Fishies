package com.example.fishies.repository

import com.example.fishies.api.RetrofitFishInstance
import com.example.fishies.api.RetrofitUserInstance
import com.example.fishies.model.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response

class UserRepository {
    suspend fun getUserState(id: String): Response<User> {
        return RetrofitUserInstance.userApi.getUserState(id)
    }

    suspend fun updateUserState(user:User){
        var userParams = JSONObject()
        userParams.put("fishes", user.fishes)
        userParams.put("money", user.money)
        userParams.put("tackleBox", user.tackleBox)
        userParams.put("equippedFishingRod", user.equippedFishingRod)
        userParams.put("unlockedBaits", user.equippedBaits)
        userParams.put("location", user.location)
        userParams.put("lastUnlockedAngler", user.lastUnlockedAngler)
        userParams.put("lastUnlockedFish", user.lastUnlockedFish)
        userParams.put("lastLoggedIn", user.lastLoggedIn)

        val userParamsString = userParams.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = userParamsString.toRequestBody("fishies/json".toMediaTypeOrNull())
        return RetrofitUserInstance.userApi.updateUserState(url = "user/${user.id}", body = requestBody)
    }
}