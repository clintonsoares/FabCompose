package com.example.fabcompose.ui.screens.dailyTasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fabcompose.ui.custom.BackIconButton
import com.example.fabcompose.ui.theme.GreenGrey80
import com.example.fabcompose.ui.theme.PrimaryColor
import com.example.fabcompose.ui.theme.Red700
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
    var isAddTaskClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBarLayout(
            navigator = navigator,
            onAddClick = {
                isAddTaskClicked = true
            }
        )}
    ){ screenPadding ->
        Surface(
            modifier = Modifier
                .padding(screenPadding)
                .fillMaxSize()
        ) {
            var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
            var addTaskText by remember { mutableStateOf(TextFieldValue("")) }
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
                    shape = RoundedCornerShape(50.dp),
                    singleLine = true,
                    enabled = !isAddTaskClicked
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
            if (isAddTaskClicked) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .alpha(0.6f)
                            .background(Color.Black)
                    )
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                            .background(GreenGrey80, shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 40.dp, vertical = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Add New Task", color = Color.White)
                            TextField(
                                value = addTaskText,
                                onValueChange = {
                                    addTaskText = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    placeholderColor = Color.DarkGray,
                                    textColor = Color.White,
                                    cursorColor = PrimaryColor,
                                    focusedIndicatorColor = PrimaryColor
                                )
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(
                                    onClick = { isAddTaskClicked = false },
                                    modifier = Modifier
                                        .size(50.dp)
                                        .shadow(elevation = 4.dp, shape = CircleShape)
                                        .background(Color.Red, shape = CircleShape)
                                        .border(width = 1.dp, Color.White, shape = CircleShape)
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "cancelAddBtn",
                                        tint = Color.White
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        viewModel.addTaskToList(addTaskText.text)
                                        isAddTaskClicked = false
                                    },
                                    modifier = Modifier
                                        .size(50.dp)
                                        .shadow(elevation = 4.dp, shape = CircleShape)
                                        .background(Color.Green, shape = CircleShape)
                                        .border(width = 1.dp, Color.White, shape = CircleShape)
                                ) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "addTaskBtn",
                                        tint = Color.White
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun TopBarLayout(
    navigator: DestinationsNavigator,
    onAddClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = StringConstants.DAILY_TASKS)
        },
        navigationIcon = {
            BackIconButton(navigator = navigator)
        },
        actions = {
            IconButton(onClick = {
                onAddClick.invoke()
            }) {
                Icon(Icons.Filled.AddCircle, "addTask")
            }
        },
        backgroundColor = PrimaryColor
    )
}