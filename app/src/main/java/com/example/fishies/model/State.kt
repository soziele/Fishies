package com.example.fishies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class State (
    var fishCount: MutableLiveData<Float> = MutableLiveData(0F),
    var moneyCount: MutableLiveData<Float> = MutableLiveData(0F)
)