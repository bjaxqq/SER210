package edu.quinnipiac.assignment2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // MutableLiveData to hold the player's name
    val playerName = MutableLiveData<String>()
}