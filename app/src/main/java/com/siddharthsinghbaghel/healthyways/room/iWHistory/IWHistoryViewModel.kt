package com.siddharthsinghbaghel.healthyways.room.iWHistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IWHistoryViewModel(application: Application): AndroidViewModel(application) {

    private val iwRepository: IWHistoryRepository
    val allIwHistory: LiveData<List<IWHistoryEntity>>
    init{
        val dao = IWHistoryDatabase.getDatabase(application).getIWHistoryDao()
        iwRepository = IWHistoryRepository(dao)
        allIwHistory = iwRepository.allIWHistory
    }

    /*viewModelScope.launch(Dispatchers.IO) - coroutines used to avoid async task*/
    fun deleteIWHistory(iwHistory : IWHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        iwRepository.delete(iwHistory)
    }

    fun insertIWHistory(iwHistory : IWHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        iwRepository.insert(iwHistory)
    }

}