package com.siddharthsinghbaghel.healthyways.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMIHistoryViewModel(application: Application): AndroidViewModel(application) {

    private val bmiRepository: BMIHistoryRepository
    val allBmiHistory: LiveData<List<BMIHistoryEntity>>
    init{
        val dao = BMIHistoryDatabase.getDatabase(application).getBMIHistoryDao()
        bmiRepository = BMIHistoryRepository(dao)
        allBmiHistory = bmiRepository.allBMIHistory
    }

    /*viewModelScope.launch(Dispatchers.IO) - coroutines used to avoid async task*/
    fun deleteBMIHistory(bmiHistory : BMIHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        bmiRepository.delete(bmiHistory)
    }

    fun insertBMIHistory(bmiHistory : BMIHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        bmiRepository.insert(bmiHistory)
    }

}