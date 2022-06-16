package com.example.fabcompose.ui.screens.dailyTasks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DailyTasksViewModel: ViewModel() {
    private var defaultTasksList = listOf("Citizen", "Operator", "Scheduler")
    private var userMap = mapOf(
        Pair("Citizen","21-06-2022"),
        Pair("Operator","19-06-2022"),
        Pair("Scheduler","17-06-2022")
    )
    private var usersList = listOf(
        Pair("Citizen","21-06-2022"),
        Pair("Operator","19-06-2022"),
        Pair("Scheduler","17-06-2022")
    )
    val filteredList: MutableState<List<String>> = mutableStateOf(emptyList())
    val filteredMap: MutableState<Map<String,String>> = mutableStateOf(emptyMap())
    val filteredPairs: MutableState<List<Pair<String,String>>> = mutableStateOf(emptyList())

    init {
        filteredList.value = defaultTasksList
        filteredPairs.value = usersList
        filteredMap.value = userMap
    }

    fun searchTaskByQuery(query: String){
        if (query.isBlank() || query.isEmpty()){
            filteredList.value = defaultTasksList
        } else {
            val tempList = mutableListOf<String>()
            defaultTasksList.forEach {
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

    fun addTaskToList(task: String){
        defaultTasksList.toMutableList().let {
            it.add(task)
            defaultTasksList = it
            filteredList.value = defaultTasksList
        }
    }

}