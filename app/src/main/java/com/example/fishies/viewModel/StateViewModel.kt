package com.example.fishies.viewModel
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.fishies.model.State
import com.google.gson.Gson
import java.lang.Exception

class StateViewModel(application: Application): AndroidViewModel(application) {
    private val filename = "state.json"
    companion object {
        lateinit var state: State;
        fun isStateInitialized() = ::state.isInitialized
    }

    init {
        try {
            if(!isStateInitialized()) {
                // if file exists, load it
                application.openFileInput(filename).bufferedReader().use { data ->
                    state = Gson().fromJson<State>(data, State::class.java)
                }
            }
        }
        catch(e: Exception) {
            // if file doesn't exist, initialize state with basic values
            state = State()
        }
    }

    private fun writeStateToFile() {
        getApplication<Application>().openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(Gson().toJson(state).toByteArray())
        }
    }

    fun getState(): State = state

    fun click() {
        // todo: Add upgrade logic
        state.fishCount.value = state.fishCount.value?.plus(1)
    }

    fun sellFishes() {
        // todo: Add selling logic if selling prices can increase
        state.moneyCount.value = state.moneyCount.value?.plus(state.fishCount.value!!)
        state.fishCount.value = 0F
    }
}