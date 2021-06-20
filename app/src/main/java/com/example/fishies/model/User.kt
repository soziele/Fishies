package com.example.fishies.model

import java.util.*
import kotlin.collections.ArrayList

data class User(val id: Int,
                var new: Boolean,
                var fishes: Int,
                var money: Int,
                var unlockedTackleBox: TackleBox,
                var equippedFishingRod: Rod,
                var location: Location,
                var unlockedAnglers: ArrayList<Angler>,
                var lastLoggedIn: Date) {
}