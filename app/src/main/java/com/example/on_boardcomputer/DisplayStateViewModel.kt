package com.example.on_boardcomputer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayStateViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _tOnBoard = MutableLiveData<Int>()
    val tOnBoard: LiveData<Int>
        get() = _tOnBoard
    private val _tEngine = MutableLiveData<Int>()
    val tEngine: LiveData<Int>
        get() = _tEngine
    private val _voltage = MutableLiveData<Int>()
    val voltage: LiveData<Int>
        get() = _voltage

    fun voltageChange(){
        _voltage.value = (_voltage.value)?.plus(5)
    }
}