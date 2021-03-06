package `in`.iot.lab.healthyways.room.history


import `in`.iot.lab.healthyways.room.history.entities.*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BMIHistoryEntity::class,
                      IWHistoryEntity::class,
                      FatCalcHistoryEntity::class,
                      OneRMCalcHistoryEntity::class,
                      BMRCalcHistoryEntity::class,
                      GCHistoryEntity::class], version = 1, exportSchema = false)
public abstract class HistoryDatabase : RoomDatabase() {

    abstract fun getHistoryDao() : HistoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getDatabase(context: Context): HistoryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                        HistoryDatabase::class.java,
                    "history_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}