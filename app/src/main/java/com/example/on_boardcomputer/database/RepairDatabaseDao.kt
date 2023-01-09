package com.example.on_boardcomputer.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RepairDatabaseDao {

    @Insert
    suspend fun insert(repair: Repair)

    @Update
    suspend fun update(repair: Repair)

    @Delete
    suspend fun delete(repair: Repair)

    @Query("SELECT * from history_of_repairs WHERE repairId = :key")
    fun get(key: Long): Repair?

    @Query("SELECT * from history_of_repairs ORDER BY repairId DESC")
    fun getAll(): LiveData<List<Repair>>

//    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC LIMIT 1")
//    suspend fun getCurrentMeasurement(): AverageStat?

    @Query("DELETE FROM mid_measuring_table")
    suspend fun clear()
}