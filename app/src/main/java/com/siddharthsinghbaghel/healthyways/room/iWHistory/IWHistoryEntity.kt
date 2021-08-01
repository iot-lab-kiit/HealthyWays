package com.siddharthsinghbaghel.healthyways.room.iWHistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IwHistory")
data class IWHistoryEntity(@ColumnInfo(name = "IW Value") var IWValue: String,
                    @ColumnInfo(name = "IW Weight") var iwWeight: String,
                    @ColumnInfo(name = "IW Height") var iwHeight: String,
                    @ColumnInfo(name = "IW DateTime") var iwDateT: String)
                    {
    @PrimaryKey(autoGenerate = true) var id = 0
}
