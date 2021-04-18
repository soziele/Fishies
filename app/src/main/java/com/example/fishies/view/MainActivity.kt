package com.example.fishies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fishies.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_main)
    }
}