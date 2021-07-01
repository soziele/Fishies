package com.example.fishies.repository

import android.util.Log
import com.example.fishies.api.RetrofitUserInstance
import com.example.fishies.model.User
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import okio.IOException
import org.json.JSONObject
import retrofit2.Response

class UserRepository {
    suspend fun getUserState(id: String): Response<User> {
        return RetrofitUserInstance.userApi.getUserState(id = id)
    }

    suspend fun updateUserState(user: User){
        var userParams = JSONObject()
        userParams.put("fishes", user.fishes)
        userParams.put("money", user.money)
        userParams.put("tackleBox", user.tackleBox)
        userParams.put("equippedFishingRod", user.equippedFishingRod)
        userParams.put("location", user.location)
        userParams.put("lastUnlockedBait", user.lastUnlockedBait)
        userParams.put("lastUnlockedAngler", user.lastUnlockedAngler)
        userParams.put("lastUnlockedFish", user.lastUnlockedFish)

        val userParamsString = userParams.toString()
        Log.d("Updating user", user.location.toString())

        val mediaType = "application/json; charset=utf-8".toMediaType()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = userParamsString.toRequestBody(mediaType)
        Log.d("Request body", requestBody.contentLength().toString())
        bodyToString(requestBody)?.let { Log.d("Request body", it) }
        RetrofitUserInstance.userApi.updateUserState(body = requestBody, id = user.id.toString())
    }

    suspend fun deleteUserState(id: String){
        return RetrofitUserInstance.userApi.deleteUserState(id = id)
    }

    private fun bodyToString(request: RequestBody): String? {
        return try {
            val buffer = Buffer()
            request!!.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}