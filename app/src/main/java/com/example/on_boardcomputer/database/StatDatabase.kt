package com.example.on_boardcomputer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MiddleStat::class], version = 2, exportSchema = false)
abstract class StatDatabase: RoomDatabase() {
    abstract val statDatabaseDao: StatDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: StatDatabase? = null

        fun getInstance(context: Context): StatDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StatDatabase::class.java,
                        "stat_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}