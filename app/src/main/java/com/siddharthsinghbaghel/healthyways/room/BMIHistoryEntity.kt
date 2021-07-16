package com.siddharthsinghbaghel.healthyways.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BMIHistory")
class BMIHistoryEntity(@ColumnInfo(name = "BMI Value") var bmiValue: String,
                    @ColumnInfo(name = "BMI State") var bmiState: String,
                    @ColumnInfo(name = "BMI Weight") var bmiWeight: String,
                    @ColumnInfo(name = "BMI Height") var bmiHeight: String )
                    {
    @PrimaryKey(autoGenerate = true) var id = 0
}
