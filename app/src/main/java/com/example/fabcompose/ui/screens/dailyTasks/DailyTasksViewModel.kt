package com.example.fabcompose.ui.screens.dailyTasks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DailyTasksViewModel: ViewModel() {
    private val defaultTasksList = listOf("Drink Water", "Walk", "Run")
    val filteredList: MutableState<List<String>> = mutableStateOf(emptyList())

    init {
        filteredList.value = defaultTasksList
    }

    fun searchTaskByQuery(query: String){
        if (query.isBlank() || query.isEmpty()){
            filteredList.value = defaultTasksList
        } else {
            val tempList = mutableListOf<String>()
            filteredList.value.forEach {
                if(it.contains(query, ignoreCase = true)){
                    tempList.add(it)
                }
            }
            if (tempList.isNotEmpty()){
                filteredList.value = tempList
            } else {
                filteredList.value = emptyList()
            }
        }
    }

}