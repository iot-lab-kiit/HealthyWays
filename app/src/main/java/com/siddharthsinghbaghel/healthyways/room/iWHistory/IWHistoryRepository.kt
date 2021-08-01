package com.siddharthsinghbaghel.healthyways.room.iWHistory


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class IWHistoryRepository(private val historyDao : IWHistoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    val allIWHistory: LiveData<List<IWHistoryEntity>> = historyDao.getAllIWHistory()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(iwHistory: IWHistoryEntity) {
        historyDao.insertIwHistory(iwHistory)
    }
    suspend fun delete(iwHistory: IWHistoryEntity) {
        historyDao.deleteIwHistory(iwHistory)
    }
}