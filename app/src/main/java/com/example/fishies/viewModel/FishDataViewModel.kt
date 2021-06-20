package com.example.fishies.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fishies.model.FishData
import com.example.fishies.repository.FishRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class FishDataViewModel(private val repository: FishRepository) : ViewModel() {

    var responseFishData: MutableLiveData<Response<FishData>> = MutableLiveData()
    var fishList: MutableLiveData<MutableList<FishData>> = MutableLiveData<MutableList<FishData>>()

    fun getFishByName(name: String) {

        val url = "fish/getbyname/${name}"

        viewModelScope.launch {
            val response = repository.getFish(url)
            responseFishData.value = response
        }
    }

    fun getAllTheFish() {
        
        viewModelScope.launch {

            var tmpList: MutableList<FishData> = mutableListOf()
            for(i in 1..25) {

                val url = "fish/${i}"
                val unlocked: Boolean = i <= 5

                val response = repository.getFish(url)
                responseFishData.value = response
                if (response.isSuccessful) {
                    Log.d("Response", "${response.body()?.name.toString()} \n ${response.body()?.biology.toString()}")
                    tmpList.add(FishData(response.body()?.avLength, response.body()?.biology, response.body()?.description, response.body()?.environment, response.body()?.id, response.body()?.maxAge, response.body()?.maxClimate, response.body()?.maxDepth, response.body()?.maxLength, response.body()?.maxWeight, response.body()?.minClimate, response.body()?.minDepth, response.body()?.name, response.body()?.scientific, unlocked, response.body()!!.id!! * response.body()!!.id!!))
                } else {
                    Log.e("Response", response.errorBody().toString())
                }
                fishList.value = tmpList
            }

        }
    }
}