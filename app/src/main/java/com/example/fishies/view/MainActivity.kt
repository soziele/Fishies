package com.example.fishies.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.fishies.R
import com.example.fishies.model.UpgradesList
import com.example.fishies.repository.FishRepository
import com.example.fishies.repository.UserRepository
import com.example.fishies.viewModel.FishDataViewModel
import com.example.fishies.viewModel.FishDataViewModelFactory
import com.example.fishies.viewModel.StateViewModel
import com.example.fishies.viewModel.StateViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var fishDataVM: FishDataViewModel
    private lateinit var stateViewModel: StateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_main)

        val repository = FishRepository()
        val viewModelFactory = FishDataViewModelFactory(repository)
        fishDataVM = ViewModelProvider(this, viewModelFactory).get(FishDataViewModel::class.java)

        fishDataVM.getAllTheFish()

        val userRepository = UserRepository()
        val stateViewModelFactory = StateViewModelFactory(userRepository)
        stateViewModel = ViewModelProvider(this, stateViewModelFactory).get(StateViewModel::class.java)

        val dialogBuilder = AlertDialog.Builder(this)
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()

                })

        stateViewModel.User.observe(this, Observer{user->

            if(user.lastUnlockedAngler != null && stateViewModel.welcomeDialogTime){
                val alert = dialogBuilder.create()
                alert.setTitle("Welcome back!")
                alert.setMessage("${UpgradesList.anglers[user.lastUnlockedAngler!!].name} worked for you when you were gone and now you've got ${user.fishes.toInt()} fishes.")
                alert.setIcon(R.drawable.fisherman)
                alert.show()
                stateViewModel.welcomeDialogTime = false
            }
        })
    }

}