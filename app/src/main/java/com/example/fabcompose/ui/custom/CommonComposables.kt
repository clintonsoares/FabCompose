package com.example.fabcompose.ui.custom

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BackIconButton(navigator: DestinationsNavigator){
    IconButton(onClick = {
        navigator.navigateUp()
    }) {
        Icon(Icons.Filled.ArrowBack, "backIcon")
    }
}