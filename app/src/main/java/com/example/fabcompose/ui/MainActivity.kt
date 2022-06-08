package com.example.fabcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fabcompose.ui.screens.FabAnimLayout
import com.example.fabcompose.ui.screens.NavGraphs
import com.example.fabcompose.ui.screens.UserSelectionLayout
import com.example.fabcompose.ui.theme.FabComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FabComposeTheme {
//                FabAnimLayout()
//                UserSelectionLayout()
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
