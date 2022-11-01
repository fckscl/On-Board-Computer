package com.example.on_boardcomputer.ui.repair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.on_boardcomputer.database.Repair
import com.example.on_boardcomputer.database.RepairDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.util.*

class RecordingViewModel (
    val database: RepairDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    suspend fun addRepair(name: String, date: Long, cost: Int){
        val repair = Repair(nameOfRepair = name, dateOfRepair = date, costOfRepair = cost)
        
        withContext(Dispatchers.IO) {
            database.insert(repair)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}