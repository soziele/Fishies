package com.example.fishies.model

import java.time.DateTimeException
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

data class User(val id: Int = -1,
                var new: Boolean = true,
                var fishes: Int = 0,
                var money: Int = 0,
                var tackleBox: Int = 0,
                var equippedFishingRod: Int = -1,
                var equippedBaits: List<Int> = emptyList<Int>(),
                var location: Int = 0,
                var unlockedAnglers: List<Int> = emptyList<Int>(),
                var lastLoggedIn: Long = 1623795066013) {
}