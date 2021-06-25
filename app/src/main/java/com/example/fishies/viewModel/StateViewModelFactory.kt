package com.example.fishies.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fishies.repository.UserRepository

class StateViewModelFactory (val repositoryUser: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java).newInstance(repositoryUser) as T
    }
}