package com.example.on_boardcomputer.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StatDatabaseDao {

    @Insert
    suspend fun insert(night: AverageStat)

    @Update
    suspend fun update(night: AverageStat)

    @Delete
    suspend fun delete(night: AverageStat)

    @Query("SELECT * from mid_measuring_table WHERE statId = :key")
    fun get(key: Long): AverageStat?

    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC")
    fun getAll(): LiveData<List<AverageStat>>

    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC LIMIT 1")
    suspend fun getCurrentMeasurement(): AverageStat?

    @Query("DELETE FROM mid_measuring_table")
    suspend fun clear()
}