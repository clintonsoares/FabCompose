package com.example.fabcompose.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fabcompose.ui.custom.BackIconButton
import com.example.fabcompose.utils.StringConstants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun DailyTasksLayout(
    navigator: DestinationsNavigator
){
    Scaffold(
        topBar = { TopBarLayout(navigator) }
    ){ screenPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenPadding)
        ) {
            Text(text = "Hi")
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
        }
    )
}