package com.siddharthsinghbaghel.healthyways.room.history


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.siddharthsinghbaghel.healthyways.room.history.entities.BMRCalcHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.FatCalcHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.GCHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.OneRMCalcHistoryEntity

class HistoryRepository(private val historyDao : HistoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    val allFatHistory: LiveData<List<FatCalcHistoryEntity>> = historyDao.getAllFatHistory()
    val allORMHistory: LiveData<List<OneRMCalcHistoryEntity>> = historyDao.getAllORMHistory()
    val allBMRHistory: LiveData<List<BMRCalcHistoryEntity>> = historyDao.getAllBMRHistory()
    val allGCHistory: LiveData<List<GCHistoryEntity>> = historyDao.getAllGCHistory()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFat(fatHistory: FatCalcHistoryEntity) {
        historyDao.insertFatHistory(fatHistory)
    }
    suspend fun deleteFat(fatHistory: FatCalcHistoryEntity) {
        historyDao.deleteFatHistory(fatHistory)
    }
    suspend fun insertORM(ormHistory: OneRMCalcHistoryEntity) {
        historyDao.insertORMHistory(ormHistory)
    }
    suspend fun deleteORM(ormHistory: OneRMCalcHistoryEntity) {
        historyDao.deleteORMHistory(ormHistory)
    }
    suspend fun insertBMR(bmrHistory: BMRCalcHistoryEntity) {
        historyDao.insertBMRHistory(bmrHistory)
    }
    suspend fun deleteBMR(bmrHistory: BMRCalcHistoryEntity) {
        historyDao.deleteBMRHistory(bmrHistory)
    }
    suspend fun insertGC(gcHistory: GCHistoryEntity) {
        historyDao.insertGCHistory(gcHistory)
    }
    suspend fun deleteGC(gcHistory: GCHistoryEntity) {
        historyDao.deleteGCHistory(gcHistory)
    }


}