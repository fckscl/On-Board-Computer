package com.example.on_boardcomputer.ui.display

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.on_boardcomputer.database.StatDatabaseDao

class DisplayStateViewModelFactory (
    private val dataSource: StatDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayStateViewModel::class.java)) {
            return DisplayStateViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}