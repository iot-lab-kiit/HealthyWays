package `in`.iot.lab.healthyways.room.history.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FatCalcHistory")
data class FatCalcHistoryEntity(@ColumnInfo(name = "Fat Perc") var fatPerc: String,
                           @ColumnInfo(name = "Fat Mass") var fatMass: String,
                           @ColumnInfo(name = "Age") var ageValue: String,
                           @ColumnInfo(name = "BMI") var bmiValue: String,
                           @ColumnInfo(name = "Fat DateTime") var fatDateT: String)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}
