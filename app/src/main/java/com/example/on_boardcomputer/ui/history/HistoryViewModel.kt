package com.example.on_boardcomputer.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.on_boardcomputer.database.MiddleStat
import com.example.on_boardcomputer.database.StatDatabaseDao
import com.example.on_boardcomputer.util.formatMeasurements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(
    val db : StatDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

//    private var startMeasurement = List<MutableLiveData<MiddleStat?>>(5)
    private val measurements = db.getAll()

    val measurementsString = Transformations.map(measurements){ item ->
        formatMeasurements(item, application.resources)
    }

//    init {
//        initializeTonight()
//    }
//
//    private fun initializeTonight() {
//        uiScope.launch {
//            startMeasurement.value = getTonightFromDatabase()
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}