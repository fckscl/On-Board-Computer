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

    @Query("SELECT * from history_of_repairs ORDER BY date_repair DESC")
    fun getAll(): LiveData<List<Repair>>

//    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC LIMIT 1")
//    suspend fun getCurrentMeasurement(): AverageStat?

    @Query("DELETE FROM history_of_repairs")
    suspend fun clear()

    @Query("SELECT SUM(cost_repair) FROM history_of_repairs")
    fun sum(): Double?
}