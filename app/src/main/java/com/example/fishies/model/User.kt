package com.example.fishies.model

import java.time.DateTimeException
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

data class User(val id: String = "",
                var new: Boolean = true,
                var fishes: Float = 0f,
                var money: Int = 0,
                var tackleBox: Int = 0,
                var equippedFishingRod: Int = -1,
                var lastUnlockedBait: Int = 0,
                var location: Int = 0,
                var lastUnlockedAngler: Int? = null,
                var lastLoggedIn: Long = 1623795066013,
                var lastUnlockedFish: Int = 0
                ){
}