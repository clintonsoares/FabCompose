package com.example.fabcompose.ui.screens.dailyTasks

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabcompose.database.entities.DailyTaskEntity
import com.example.fabcompose.repositories.DailyTaskRepository
import com.example.fabcompose.utils.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyTasksViewModel(appObj: Application) : AndroidViewModel(appObj) {
    private val dailyTaskRepository: DailyTaskRepository = DailyTaskRepository(appObj)
    private val dailyTasksList: MutableState<List<DailyTaskEntity>> = mutableStateOf(emptyList())
    val filteredList: MutableState<List<DailyTaskEntity>> = mutableStateOf(emptyList())
    val isLoading = mutableStateOf(false)
    val deleteTaskId = mutableStateOf(0)

    fun onScreenCreated() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getAllDailyTasks()
            } catch (e: Exception) {
                Log.e("error-----------", e.message.toString())
            }
        }
    }

    fun onAddTaskClicked(title: String, comments: String) {
        val userId = "6afc89we45tg"
        val date = Utilities.getCurrentDate()

        val createdTask = DailyTaskEntity(
            title = title,
            userId = userId,
            taskDate = date,
            isCompleted = false,
            comments = comments
        )
        addTaskToDb(createdTask)
    }

    fun onDeleteConfirmClicked() {
        deleteTaskFromDb(deleteTaskId.value)
    }

    fun searchTaskByQuery(query: String) {
        if (query.isBlank() || query.isEmpty()) {
            filteredList.value = dailyTasksList.value
        } else {
            val tempList = mutableListOf<DailyTaskEntity>()
            dailyTasksList.value.forEach {
                if (it.title.contains(query, ignoreCase = true)) {
                    tempList.add(it)
                }
            }
            if (tempList.isNotEmpty()) {
                filteredList.value = tempList
            } else {
                filteredList.value = emptyList()
            }
        }
    }



    /**
     * Private DB Functions
     **/

    private fun getAllDailyTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedTasks = dailyTaskRepository.fetchAllDailyTasks()
            dailyTasksList.value = fetchedTasks
            filteredList.value = fetchedTasks
        }
    }

    private fun addTaskToDb(task: DailyTaskEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dailyTaskRepository.insertDailyTask(task)
            getAllDailyTasks()
        }
    }

    private fun deleteTaskFromDb(taskId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dailyTaskRepository.deleteDailyTaskById(taskId)
            getAllDailyTasks()
        }
    }

}