package com.siddharthsinghbaghel.healthyways.room.bMIHistory

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BMIHistoryRepository(private val historyDao : BMIHistoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    val allBMIHistory: LiveData<List<BMIHistoryEntity>> = historyDao.getAllBMIHistory()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bmiHistory: BMIHistoryEntity) {
        historyDao.insertBmiHistory(bmiHistory)
    }
    suspend fun delete(bmiHistory: BMIHistoryEntity) {
        historyDao.deleteBmiHistory(bmiHistory)
    }
}