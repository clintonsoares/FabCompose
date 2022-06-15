package com.example.fabcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fabcompose.ui.screens.NavGraphs
import com.example.fabcompose.ui.theme.FabComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FabComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
