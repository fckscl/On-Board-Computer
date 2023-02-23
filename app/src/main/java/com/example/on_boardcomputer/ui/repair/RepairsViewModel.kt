package com.example.on_boardcomputer.ui.repair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.on_boardcomputer.database.RepairDatabaseDao
import kotlinx.coroutines.*

class RepairsViewModel(
    val dataSource: RepairDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    var sum = dataSource.sum()

//    private suspend fun getSum(): Double {
//        return withContext(Dispatchers.IO) {
//            var measurement = dataSource.sum()
//
//            if (measurement == null) {
//                measurement = 0.0
//            }
//
//            measurement
//        }
//    }

    val measurements = dataSource.getAll()
//    fun getSumSync(){
//        uiScope.launch {
//            sum = getSum()
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }
//    init {
////        getSumSync()
//        sum = 0.0
//    }
}