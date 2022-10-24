package com.example.on_boardcomputer.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.on_boardcomputer.database.StatDatabaseDao
import com.example.on_boardcomputer.util.formatMeasurements
import kotlinx.coroutines.*

class HistoryViewModel(
    val db : StatDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    val measurements = db.getAll()

    val measurementsString = Transformations.map(measurements){ item ->
        formatMeasurements(item, application.resources)
    }

    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            db.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}