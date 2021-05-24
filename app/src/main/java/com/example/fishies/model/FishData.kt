package com.example.fishies.model

data class FishData(
    val avLength: Double? = 0.0,
    val biology: String? = "",
    val description: String? = "",
    val environment: String? = "",
    val id: Int? = 0,
    val maxAge: Int? = 0,
    val maxClimate: Double? = 0.0,
    val maxDepth: Double? = 0.0,
    val maxLength: Double? = 0.0,
    val maxWeight: Double? = 0.0,
    val minClimate: Double? = 0.0,
    val minDepth: Double? = 0.0,
    val name: String? = "",
    val scientific: String? = "",
    val unlocked: Boolean? = false,
    var value: Int? = 1
)