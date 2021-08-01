package com.siddharthsinghbaghel.healthyways.room.iWHistory

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface IWHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIwHistory(history: IWHistoryEntity)
    @Delete
    suspend fun deleteIwHistory(history: IWHistoryEntity)
    @Query("select * from IwHistory")
    fun getAllIWHistory(): LiveData<List<IWHistoryEntity>>


}