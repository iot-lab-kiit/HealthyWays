package `in`.iot.lab.healthyways.room.history.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BMRCalcHistory")
data class BMRCalcHistoryEntity(@ColumnInfo(name = "Exercise Extent") var exerExtent: String,
                                @ColumnInfo(name = "Weight") var weightValue: String,
                                @ColumnInfo(name = "Height") var heightValue: String,
                                @ColumnInfo(name = "BMR DateTime") var bmrDateT: String,
                                @ColumnInfo(name = "BMR Value") var bmrValue: String,
                                @ColumnInfo(name = "TDEE Value") var tdeeValue: String,)

{
    @PrimaryKey(autoGenerate = true) var id = 0
}
