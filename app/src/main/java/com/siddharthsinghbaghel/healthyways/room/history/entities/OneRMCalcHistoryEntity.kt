package com.siddharthsinghbaghel.healthyways.room.history.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ORMCalcHistory")
data class OneRMCalcHistoryEntity(@ColumnInfo(name = "Exercise") var exerChoosed: String,
                                  @ColumnInfo(name = "Weight") var weightValue: String,
                                  @ColumnInfo(name = "Repetitions") var repValue: String,
                                  @ColumnInfo(name = "OneRM Value") var oneRMValue: String,
                                  @ColumnInfo(name = "OneRM DateTime") var ormDateT: String)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}
