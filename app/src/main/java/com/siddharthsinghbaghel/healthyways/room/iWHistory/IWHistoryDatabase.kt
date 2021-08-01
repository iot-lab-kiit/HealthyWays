package com.siddharthsinghbaghel.healthyways.room.iWHistory


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [IWHistoryEntity::class], version = 1, exportSchema = false)
public abstract class IWHistoryDatabase : RoomDatabase() {

    abstract fun getIWHistoryDao() : IWHistoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: IWHistoryDatabase? = null

        fun getDatabase(context: Context): IWHistoryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                        IWHistoryDatabase::class.java,
                    "iw_history_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}