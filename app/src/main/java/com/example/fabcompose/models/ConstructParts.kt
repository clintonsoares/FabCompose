package com.example.fabcompose.models

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.fabcompose.models.composableExtensions.*
import com.example.fabcompose.models.type.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.fabcompose.R
import com.example.fabcompose.ui.custom.ExpandableCard

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ConstructPart(
    listItems: ListItems,
    modifier: Modifier = Modifier
) {
    when (listItems.type) {
        Type.TEXT -> {
            val text = (listItems as Text).message
            val textAlign = when (listItems.textAlign) {
                Align.START -> TextAlign.Start
                Align.END -> TextAlign.End
                Align.CENTER -> TextAlign.Center
                else -> null
            }
            val fontSize = when (listItems.textFont) {
                FontSize.TINY -> 8.sp
                FontSize.SMALL -> 12.sp
                FontSize.BIG -> 20.sp
                FontSize.HUGE -> 24.sp
                else -> 16.sp
            }
            Text(
                text = text,
                modifier = modifier,
                textAlign = textAlign,
                fontSize = fontSize
            )
        }
        Type.BUTTON -> {
            val text = (listItems as Button).message
            Button(onClick = { /*TODO*/ }, modifier = modifier) {
                Text(text = text)
            }
        }
        Type.IMAGE -> {
            val imageAlign = when ((listItems as Image).imageAlign) {
                Align.START -> Alignment.TopStart
                Align.END -> Alignment.BottomEnd
                else -> Alignment.Center
            }
            val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
            Image(image, "", modifier = modifier, alignment = imageAlign)
        }
        Type.EXPANDABLE -> {
            val expandable = (listItems as Expandable)
            ExpandableCard (
                title = expandable.message,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                for (item in expandable.listItems) {
                    ConstructPart(item, createModifier(item))
                }
            }
        }
        Type.COLUMN -> {
            val items = (listItems as Column).listItems
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                for (item in items) {
                    ConstructPart(item, createModifier(item))
                }
            }
        }
        Type.ROW -> {
            val items = (listItems as Row).listItems
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                for (item in items) {
                    ConstructPart(item, createModifier(item))
                }
            }
        }
    }
}