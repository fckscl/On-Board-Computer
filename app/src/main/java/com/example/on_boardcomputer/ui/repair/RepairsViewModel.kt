package com.example.on_boardcomputer.ui.repair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.on_boardcomputer.database.RepairDatabaseDao
import kotlinx.coroutines.*

class RepairsViewModel(
    val dataSource: RepairDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    var sum = dataSource.sum()

    val measurements = dataSource.getAll()
}