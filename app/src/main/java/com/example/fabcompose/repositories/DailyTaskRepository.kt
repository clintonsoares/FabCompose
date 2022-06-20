package com.example.fabcompose.repositories

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import com.example.fabcompose.database.DailyTaskRoomDb
import com.example.fabcompose.database.dao.DailyTaskDao
import com.example.fabcompose.database.entities.DailyTaskEntity

class DailyTaskRepository(application: Application) {
    private var dailyTaskDao: DailyTaskDao

    init {
        val database = DailyTaskRoomDb.getDatabase(application)
        dailyTaskDao = database.dailyTaskDao()
    }

    suspend fun fetchAllDailyTasks():List<DailyTaskEntity> {
        return dailyTaskDao.fetchAllDailyTasks()
    }

    suspend fun insertDailyTask(dailyTask: DailyTaskEntity){
        dailyTaskDao.insertDailyTask(dailyTask)
    }

    suspend fun deleteDailyTaskById(id: Int){
        dailyTaskDao.deleteDailyTaskById(id)
    }

    suspend fun deleteAllDailyTasks() {
        dailyTaskDao.deleteAllDailyTasks()
    }

    suspend fun checkSingleTaskInDb(id: Int,checked: Boolean){
        dailyTaskDao.checkSingleTaskInDb(id, checked)
    }

}