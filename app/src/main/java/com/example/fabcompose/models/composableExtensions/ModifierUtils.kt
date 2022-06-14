package com.example.fabcompose.models.composableExtensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fabcompose.models.type.Align
import com.example.fabcompose.models.type.ItemColor

@Composable
fun RowScope.createModifier(
    listItems: ListItems
): Modifier {

    var modifier = if (listItems.weight > 0) {
        Modifier.weight(listItems.weight)
    } else Modifier

    modifier = commonModifier(modifier, listItems)

    return when (listItems.alignment) {
        Align.START -> modifier.align(Alignment.Top)
        Align.END -> modifier.align(Alignment.Bottom)
        Align.CENTER -> modifier.align(Alignment.CenterVertically)
        Align.FILL -> modifier.fillMaxHeight()
        else -> modifier
    }
}

@Composable
fun ColumnScope.createModifier(
    listItems: ListItems
): Modifier {
    var modifier = if (listItems.weight > 0) {
        Modifier.weight(listItems.weight)
    } else Modifier

    modifier = commonModifier(modifier, listItems)

    return when (listItems.alignment) {
        Align.START -> modifier.align(Alignment.Start)
        Align.END -> modifier.align(Alignment.End)
        Align.CENTER -> modifier.align(Alignment.CenterHorizontally)
        Align.FILL -> modifier.fillMaxWidth()
        else -> modifier
    }
}

@Composable
private fun commonModifier(
    modifier: Modifier,
    listItems: ListItems
): Modifier {
    return when (listItems.backgroundColor) {
        ItemColor.RED -> modifier.background(Color.Red)
        ItemColor.GREEN -> modifier.background(Color.Green)
        ItemColor.BLUE -> modifier.background(Color.Blue)
        else -> modifier
    }
}