package com.example.fishies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fishies.repository.FishRepository

class FishDataViewModelFactory (val repositoryFish: FishRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FishDataViewModel(repositoryFish) as T
    }
}