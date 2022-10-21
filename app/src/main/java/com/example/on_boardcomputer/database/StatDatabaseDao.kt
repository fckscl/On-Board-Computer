package com.example.on_boardcomputer.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

@Dao
interface StatDatabaseDao {

    @Insert
    fun insert(night: MiddleStat)

    @Update
    fun update(night: MiddleStat)

    @Delete
    fun delete(night: MiddleStat)

    @Query("SELECT * from mid_measuring_table WHERE statId = :key")
    fun get(key: Long): MiddleStat?

    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC")
    fun getAll(): LiveData<List<MiddleStat>>

    @Query("SELECT * from mid_measuring_table ORDER BY statId DESC LIMIT 1")
    fun getCurrentMeasurement(): MiddleStat?

    @Query("DELETE FROM mid_measuring_table")
    suspend fun clear()
}