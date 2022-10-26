package com.example.on_boardcomputer.ui.repair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.on_boardcomputer.database.RepairDatabaseDao

class RecordingViewModel (
    val dataSource: RepairDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    // TODO: Implement the ViewModel
}