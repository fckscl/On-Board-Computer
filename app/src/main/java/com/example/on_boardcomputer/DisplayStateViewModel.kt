package com.example.on_boardcomputer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayStateViewModel : ViewModel() {
    // TODO: Implement the ViewModel
//    val count =0
    private val _tOnBoard = MutableLiveData<Double>()
    val tOnBoard: LiveData<Double>
        get() = _tOnBoard
    private val _tEngine = MutableLiveData<Double>()
    val tEngine: LiveData<Double>
        get() = _tEngine
    private val _voltage = MutableLiveData<Double>()
    val voltage: LiveData<Double>
        get() = _voltage

    init {

        _voltage.value = 0.0
        _tEngine.value = 0.0
        _tOnBoard.value = 0.0
    }

    fun voltageChange(){
        Log.i("main","${_voltage.value}")
        _voltage.value =_voltage.value?.plus(1)
//        _voltage.postValue(5)

    }

}