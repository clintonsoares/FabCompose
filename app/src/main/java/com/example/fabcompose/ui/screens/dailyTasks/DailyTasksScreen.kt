package com.example.fabcompose.ui.screens.dailyTasks

import android.app.Application
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fabcompose.database.entities.DailyTaskEntity
import com.example.fabcompose.ui.custom.BackIconButton
import com.example.fabcompose.ui.custom.CirclesLoader
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
    navigator: DestinationsNavigator
) {
    var isAddTaskClicked by remember { mutableStateOf(false) }
    var isDeleteTaskClicked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val applicationObj = context.applicationContext as Application
    val viewModel = DailyTasksViewModel(applicationObj)
    viewModel.onScreenCreated()

    Scaffold(
        topBar = {
            TopBarLayout(
                navigator = navigator,
                onAddClick = {
                    isAddTaskClicked = true
                }
            )
        }
    ) { screenPadding ->
        Surface(
            modifier = Modifier
                .padding(screenPadding)
                .fillMaxSize()
        ) {
            var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
            var addTaskTitle by remember { mutableStateOf("") }
            var addTaskComments by remember { mutableStateOf("") }
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
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    enabled = !(isAddTaskClicked || isDeleteTaskClicked)
                )
                if (tasksList.isNotEmpty()) {
                    dailyTasksListView(
                        tasksList,
                        viewModel,
                        onDeleteClicked = { isDeleteTaskClicked = true }
                    )
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
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                            .background(GreenGrey80, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 40.dp, vertical = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Add New Task", color = Color.White, fontSize = 16.sp)
                            TextField(
                                value = addTaskTitle,
                                onValueChange = {
                                    addTaskTitle = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    placeholderColor = Color.DarkGray,
                                    textColor = Color.White,
                                    cursorColor = PrimaryColor,
                                    focusedIndicatorColor = PrimaryColor
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(text = "Task Name")
                                }

                            )
                            TextField(
                                value = addTaskComments,
                                onValueChange = {
                                    addTaskComments = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    placeholderColor = Color.DarkGray,
                                    textColor = Color.White,
                                    cursorColor = PrimaryColor,
                                    focusedIndicatorColor = PrimaryColor
                                ),
                                modifier = Modifier.height(120.dp),
                                maxLines = 4,
                                placeholder = {
                                    Text(text = "Comments...")
                                }
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(
                                    onClick = {
                                        isAddTaskClicked = false
                                        addTaskTitle = ""
                                        addTaskComments = ""
                                    },
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
                                        viewModel.onAddTaskClicked(addTaskTitle, addTaskComments)
                                        isAddTaskClicked = false
                                        addTaskTitle = ""
                                        addTaskComments = ""
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
            if (isDeleteTaskClicked) {
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
                            .height(200.dp)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                            .background(GreenGrey80, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 40.dp, vertical = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Confirm Task Deletion?",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(
                                    onClick = {
                                        isDeleteTaskClicked = false
                                    },
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
                                        viewModel.onDeleteConfirmClicked()
                                        isDeleteTaskClicked = false
                                    },
                                    modifier = Modifier
                                        .size(50.dp)
                                        .shadow(elevation = 4.dp, shape = CircleShape)
                                        .background(Color.Green, shape = CircleShape)
                                        .border(width = 1.dp, Color.White, shape = CircleShape)
                                ) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "DeleteTaskConfirmBtn",
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
) {
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

@Composable
fun dailyTasksListView(
    tasksList: List<DailyTaskEntity>,
    viewModel: DailyTasksViewModel,
    onDeleteClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        items(items = tasksList, itemContent = { task ->
            val checkedState = remember { mutableStateOf(task.isCompleted) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                    .background(GreenGrey80, shape = RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Box(modifier = Modifier.weight(4f, true)) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = task.title,
                                color = PrimaryColor
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = task.taskDate,
                                color = PrimaryColor
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f, true),
                        contentAlignment = Alignment.Center
                    ) {
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                viewModel.onCheckTaskClicked(task.id, it)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryColor
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f, true),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.deleteTaskId.value = task.id
                                onDeleteClicked.invoke()
                            },
                            modifier = Modifier
                                .size(50.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "deleteTaskBtn",
                                tint = Red700
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        })
    }
}