package com.siddharthsinghbaghel.healthyways.room.bMIHistory

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BMIHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBmiHistory(history: BMIHistoryEntity)
    @Delete
    suspend fun deleteBmiHistory(history: BMIHistoryEntity)
    @Query("select * from BMIHistory")
    fun getAllBMIHistory(): LiveData<List<BMIHistoryEntity>>


}