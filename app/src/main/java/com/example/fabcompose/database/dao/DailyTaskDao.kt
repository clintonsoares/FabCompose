package com.example.fabcompose.database.dao

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fabcompose.database.entities.DailyTaskEntity

@Dao
interface DailyTaskDao {

    @Query("SELECT * FROM dailyTasks")
    fun fetchAllDailyTasks(): List<DailyTaskEntity>

    @Query("SELECT * FROM dailyTasks WHERE task_date = :taskDate")
    fun fetchDailyTasksByDate(taskDate: String): List<DailyTaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyTask(dailyTask: DailyTaskEntity)

    @Query("DELETE FROM dailyTasks WHERE id = :id")
    suspend fun deleteDailyTaskById(id: Int)

    @Query("DELETE FROM dailyTasks")
    suspend fun deleteAllDailyTasks()

    @Query("UPDATE dailyTasks SET is_completed = :checked WHERE id = :id")
    suspend fun checkSingleTaskInDb(id: Int,checked: Boolean)

}