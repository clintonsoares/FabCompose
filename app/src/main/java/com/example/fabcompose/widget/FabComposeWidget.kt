package com.example.fabcompose.widget

import android.app.Application
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.CheckBox
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.Text
import com.example.fabcompose.ui.theme.PrimaryColor

class FabComposeWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val applicationObj = context.applicationContext as Application
        val viewModel = FabComposeWidgetVM(applicationObj)
        viewModel.onScreenCreated()
        val tasksList = viewModel.dailyTasksList.value
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Text(
                text = "Fabcompose Tasks",
                modifier = GlanceModifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = GlanceModifier
                    .fillMaxSize()
            ) {
                items(tasksList) { task ->
                    Row(modifier = GlanceModifier.fillMaxSize()) {
                        Text(text = task.title)
                    }
                }
            }
        }

    }
}

class FabComposeWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = FabComposeWidget()
}