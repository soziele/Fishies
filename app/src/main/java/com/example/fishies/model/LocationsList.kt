package com.example.fishies.model

import com.example.fishies.R

class LocationsList {
    companion object{
        val locations = listOf(
                Location("Pond", 0, true, R.drawable.pond),
                Location("Springs", 50, false, R.drawable.springs),
                Location("Isle", 250,false, R.drawable.isle),
                Location("Waterfall", 1000, false, R.drawable.waterfall),
                Location("Bay", 5000000, false, R.drawable.bay))
    }
}