package com.example.fishies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fishies.model.FishData
import com.example.fishies.repository.FishRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class FishDataViewModel(private val repositoryWeather: FishRepository) : ViewModel() {

    val responseFishData: MutableLiveData<Response<FishData>> = MutableLiveData()

    fun getFishByName(name: String) {

        val url = "fish/getbyname/${name}"

        viewModelScope.launch {
            val response = repositoryWeather.getFish(url)
            responseFishData.value = response
        }
    }

    fun getFishById(id: Int) {

        val url = "fish/${id}"

        viewModelScope.launch {
            val response = repositoryWeather.getFish(url)
            responseFishData.value = response
        }
    }
}