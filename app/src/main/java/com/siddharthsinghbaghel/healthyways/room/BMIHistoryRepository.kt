package com.siddharthsinghbaghel.healthyways.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BMIHistoryRepository(private val bmiHistoryDao : BMIHistoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.


    val allBMIHistory: LiveData<List<BMIHistoryEntity>> = bmiHistoryDao.getAllBMIHistory()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bmiHistory: BMIHistoryEntity) {
        bmiHistoryDao.insertBmiHistory(bmiHistory)
    }
    suspend fun delete(bmiHistory: BMIHistoryEntity) {
        bmiHistoryDao.deleteBmiHistory(bmiHistory)
    }
}