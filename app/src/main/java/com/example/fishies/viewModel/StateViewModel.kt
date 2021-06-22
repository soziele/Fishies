package com.example.fishies.viewModel

import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.example.fishies.model.User
import com.example.fishies.repository.UserRepository
import kotlinx.coroutines.launch


class StateViewModel(val repository: UserRepository): ViewModel() {
    private val filename = "state.json"
    lateinit var User: MutableLiveData<User>

    init {
       getUserState()
    }

    fun setUserState(){
        viewModelScope.launch {
            repository.updateUserState(User.value!!)
        }
    }

    fun getUserState() {
        User = MutableLiveData(User())
        viewModelScope.launch {
            val response = repository.getUserState(123.toString())
            if (response.isSuccessful) {
                Log.d(
                    "Response",
                    "${response.body()?.location.toString()} \n ${response.body()?.new.toString()}"
                )
                User.value = User(
                    id = response.body()!!.id,
                    fishes = response.body()!!.fishes,
                    money = response.body()!!.money,
                    tackleBox = response.body()!!.tackleBox,
                    equippedFishingRod = response.body()!!.equippedFishingRod,
                    location = response.body()!!.location,
                    unlockedAnglers = response.body()!!.unlockedAnglers,
                    lastLoggedIn = response.body()!!.lastLoggedIn)
            } else {
                Log.e("Response", response.code().toString() )
            }
        }
    }

    fun click(){
        User.value!!.fishes = User.value!!.fishes.plus(1)//todo: add other upgrades
        Log.d("Clicked", User.value!!.fishes.toString())

    }

    fun sellFishes() {
        User.value!!.money = User.value!!.money.plus(User.value!!.fishes) //todo: add other upgrades
        User.value!!.fishes = 0
        Log.d("Sold", User.value!!.fishes.toString())
    }
}