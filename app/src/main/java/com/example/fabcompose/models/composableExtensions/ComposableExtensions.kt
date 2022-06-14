package com.example.fabcompose.models.composableExtensions

import com.example.fabcompose.models.type.*


class Text(
    val message: String,
    val textAlign: Align = Align.CENTER,
    val textFont: FontSize = FontSize.DEFAULT,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.TEXT, alignment, weight, backgroundColor)

class Image(
    val imageAlign: Align = Align.CENTER,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.IMAGE, alignment, weight, backgroundColor)

class Button(
    val message: String,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.BUTTON, alignment, weight, backgroundColor)

/****************************************************************/

class Row(
    val listItems: List<ListItems>,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.ROW, alignment, weight, backgroundColor)

class Column(
    val listItems: List<ListItems>,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.COLUMN, alignment, weight, backgroundColor)

/****************************************************************/

class Expandable(
    val message: String,
    val listItems: List<ListItems>,
    alignment: Align = Align.CENTER,
    weight: Float = 0f,
    backgroundColor: ItemColor = ItemColor.NONE
) : ListItems(Type.EXPANDABLE, alignment, weight, backgroundColor)