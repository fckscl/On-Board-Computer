package com.example.on_boardcomputer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

@Entity(tableName = "mid_measuring_table")
class MiddleStat (
    @PrimaryKey(autoGenerate = true)
    var statId: Long = 0L,

    @ColumnInfo(name = "start_measuring_milli")
    val startMeasuringMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_measuring_milli")
    val endMeasuringMilli: Long = startMeasuringMilli,

    @ColumnInfo(name = "mid_voltage")
    val midVoltage: Int = 0,

    @ColumnInfo(name = "mid_on_board")
    val midOnBoard: Int = 0,

    @ColumnInfo(name = "mid_engine")
    val midEngine: Int = 0,
    )
