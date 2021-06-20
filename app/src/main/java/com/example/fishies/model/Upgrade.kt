package com.example.fishies.model

class Upgrade(val type: String, val name: String, val description: String, val value: Int, var amount: Int = -1, val price: Int, var bought: Boolean) {
}