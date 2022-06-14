package com.example.fabcompose.models.composableExtensions

import com.example.fabcompose.models.type.*


sealed class ListItems(
    val type: Type,
    val alignment: Align = Align.NONE,
    val weight: Float = 0f,
    val backgroundColor: ItemColor = ItemColor.NONE
)
