package com.siddharthsinghbaghel.healthyways.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BMIHistoryEntity::class), version = 1, exportSchema = false)
public abstract class BMIHistoryDatabase : RoomDatabase() {

    abstract fun getBMIHistoryDao() : BMIHistoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BMIHistoryDatabase? = null

        fun getDatabase(context: Context): BMIHistoryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BMIHistoryDatabase::class.java,
                    "bmi_history_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}