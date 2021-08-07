package com.siddharthsinghbaghel.healthyways.room.history

import androidx.lifecycle.LiveData
import androidx.room.*
import com.siddharthsinghbaghel.healthyways.room.history.entities.*


@Dao
interface HistoryDao {

/* Dao for Fat History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFatHistory(history: FatCalcHistoryEntity)
    @Delete
    suspend fun deleteFatHistory(history: FatCalcHistoryEntity)
    @Query("select * from FatCalcHistory")
    fun getAllFatHistory(): LiveData<List<FatCalcHistoryEntity>>
/* Dao for Fat History */


/* Dao for ORM History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertORMHistory(history: OneRMCalcHistoryEntity)
    @Delete
    suspend fun deleteORMHistory(history: OneRMCalcHistoryEntity)
    @Query("select * from ORMCalcHistory")
    fun getAllORMHistory(): LiveData<List<OneRMCalcHistoryEntity>>
/* Dao for ORM History */


/* Dao for BMR History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBMRHistory(history: BMRCalcHistoryEntity)
    @Delete
    suspend fun deleteBMRHistory(history: BMRCalcHistoryEntity )
    @Query("select * from BMRCalcHistory")
    fun getAllBMRHistory(): LiveData<List<BMRCalcHistoryEntity>>
/* Dao for BMR History */


/* Dao for GC History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGCHistory(history: GCHistoryEntity)
    @Delete
    suspend fun deleteGCHistory(history: GCHistoryEntity)
    @Query("select * from GCCalcHistory")
    fun getAllGCHistory(): LiveData<List<GCHistoryEntity>>
/* Dao for BMR History */

/* Dao for BMI History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBmiHistory(history: BMIHistoryEntity)
    @Delete
    suspend fun deleteBmiHistory(history: BMIHistoryEntity)
    @Query("select * from BMIHistory")
    fun getAllBMIHistory(): LiveData<List<BMIHistoryEntity>>
/* Dao for BMI History */

/* Dao for IW History */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIwHistory(history: IWHistoryEntity)
    @Delete
    suspend fun deleteIwHistory(history: IWHistoryEntity)
    @Query("select * from IwHistory")
    fun getAllIWHistory(): LiveData<List<IWHistoryEntity>>
/* Dao for IW History */

}