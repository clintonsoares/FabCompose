package com.example.fabcompose.ui.screens.dailyTasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fabcompose.ui.custom.BackIconButton
import com.example.fabcompose.ui.theme.PrimaryColor
import com.example.fabcompose.utils.StringConstants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun DailyTasksLayout(
    navigator: DestinationsNavigator,
    viewModel: DailyTasksViewModel = DailyTasksViewModel()
){
    Scaffold(
        topBar = { TopBarLayout(navigator) }
    ){ screenPadding ->
        Surface(
            modifier = Modifier
                .padding(screenPadding)
                .fillMaxSize()
        ) {
            var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
            val tasksList = viewModel.filteredList.value
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 32.dp, end = 32.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.searchTaskByQuery(it.text)
                    },
                    placeholder = {
                        Text(
                            text = "Search for a Task"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        placeholderColor = Color.DarkGray,
                        textColor = PrimaryColor,
                        focusedBorderColor = PrimaryColor,
                        cursorColor = PrimaryColor
                    ),
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp)
                )
                if (tasksList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(items = tasksList, itemContent = { taskStr ->
                            Text(
                                text = taskStr,
                                color = PrimaryColor
                            )
                        })
                    }
                } else {
                    Text(
                        text = "No matches Found",
                        color = PrimaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarLayout(
    navigator: DestinationsNavigator
){
    TopAppBar(
        title = {
            Text(text = StringConstants.DAILY_TASKS)
        },
        navigationIcon = {
            BackIconButton(navigator = navigator)
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.AddCircle, "addTask")
            }
        },
        backgroundColor = PrimaryColor
    )
}