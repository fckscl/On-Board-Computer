package com.example.on_boardcomputer.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_of_repairs")
class Repair (
    @PrimaryKey(autoGenerate = true)
    var repairId: Long = 0L,

    @ColumnInfo(name = "date_repair")
    val dateOfRepair: String = "",

    @ColumnInfo(name = "cost_repair")
    val costOfRepair: Int = 0,

    @ColumnInfo(name = "name_repair")
    val nameOfRepair: String = "",
)
