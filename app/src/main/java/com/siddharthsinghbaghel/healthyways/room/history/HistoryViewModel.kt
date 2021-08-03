package com.siddharthsinghbaghel.healthyways.room.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.siddharthsinghbaghel.healthyways.room.history.entities.BMRCalcHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.FatCalcHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.GCHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.OneRMCalcHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val historyRepository: HistoryRepository
    val allFatHistory: LiveData<List<FatCalcHistoryEntity>>
    val allORMHistory: LiveData<List<OneRMCalcHistoryEntity>>
    val allBMRHistory: LiveData<List<BMRCalcHistoryEntity>>
    val allGCHistory: LiveData<List<GCHistoryEntity>>
    init{
        val dao = HistoryDatabase.getDatabase(application).getHistoryDao()
        historyRepository = HistoryRepository(dao)
        allFatHistory = historyRepository.allFatHistory
        allORMHistory = historyRepository.allORMHistory
        allBMRHistory = historyRepository.allBMRHistory
        allGCHistory = historyRepository.allGCHistory
    }

    /*viewModelScope.launch(Dispatchers.IO) - coroutines used to avoid async task*/
    fun deleteFatHistory(fatHistory : FatCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.deleteFat(fatHistory)
    }
    fun insertFatHistory(fatHistory : FatCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.insertFat(fatHistory)
    }
    fun deleteORMHistory(ormHistory : OneRMCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.deleteORM(ormHistory)
    }
    fun insertORMHistory(ormHistory : OneRMCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.insertORM(ormHistory)
    }
    fun deleteBMRHistory(bmrHistory : BMRCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.deleteBMR(bmrHistory)
    }
    fun insertBMRHistory(bmrHistory : BMRCalcHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.insertBMR(bmrHistory)
    }
    fun deleteGCHistory(gcHistory : GCHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.deleteGC(gcHistory)
    }
    fun insertGCHistory(gcHistory :GCHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.insertGC(gcHistory)
    }

}