package com.siddharthsinghbaghel.healthyways.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BMIHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBmiHistory(contact: BMIHistoryEntity)
    @Delete
    suspend fun deleteBmiHistory(contact: BMIHistoryEntity)
    @Query("select * from BMIHistory")
    fun getAllBMIHistory(): LiveData<List<BMIHistoryEntity>>

}