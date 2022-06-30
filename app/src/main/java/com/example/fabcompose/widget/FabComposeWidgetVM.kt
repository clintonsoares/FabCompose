package com.example.fabcompose.widget

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabcompose.database.entities.DailyTaskEntity
import com.example.fabcompose.repositories.DailyTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FabComposeWidgetVM(appObj: Application) : AndroidViewModel(appObj) {
    private val dailyTaskRepository: DailyTaskRepository = DailyTaskRepository(appObj)
    val dailyTasksList: MutableState<List<DailyTaskEntity>> = mutableStateOf(emptyList())

    fun onScreenCreated() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getAllDailyTasks()
            } catch (e: Exception) {
                Log.e("error-----------", e.message.toString())
            }
        }
    }

    private fun getAllDailyTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            dailyTasksList.value = dailyTaskRepository.fetchAllDailyTasks()
        }
    }
}