package com.example.on_boardcomputer.ui.repair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.on_boardcomputer.database.Repair
import com.example.on_boardcomputer.database.RepairDatabaseDao
import kotlinx.coroutines.*
import java.util.*

class RecordingViewModel (
    val database: RepairDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private suspend fun addRepair(name: String, date: String, cost: Int){
        val repair = Repair(nameOfRepair = name, dateOfRepair = date, costOfRepair = cost)
        
        withContext(Dispatchers.IO) {
            database.insert(repair)
        }
    }

    fun add(name: String, day: Int, month: Int, year: Int, cost: Int){

        uiScope.launch {
            addRepair(name, "${day}-${month+1}-${year}", cost)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}