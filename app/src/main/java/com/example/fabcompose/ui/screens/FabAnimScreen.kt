package com.example.fabcompose.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fabcompose.utils.ClickState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FabAnimLayout(){
    var clickState by remember { mutableStateOf( ClickState.Inactive ) }
    val transition = updateTransition(targetState = clickState, label = "clickedState")

    val mContext = LocalContext.current

    val mainFabRotate by transition.animateFloat(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive -> 0f
            ClickState.Active -> 135f
        }
    }

    val fab1OffsetX by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive,
            ClickState.Active -> 0.dp
        }
    }
    val fab1OffsetY by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive -> 0.dp
            ClickState.Active -> (-80).dp
        }
    }

    val fab2OffsetX by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive -> 0.dp
            ClickState.Active -> (-64).dp
        }
    }
    val fab2OffsetY by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive -> 0.dp
            ClickState.Active -> (-64).dp
        }
    }

    val fab3OffsetX by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive -> 0.dp
            ClickState.Active -> (-80).dp
        }
    }
    val fab3OffsetY by transition.animateDp(label = "clickedState") { state ->
        when (state) {
            ClickState.Inactive,
            ClickState.Active -> 0.dp
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){

        /**
         * Fab 1 Button
         **/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 32.dp, bottom = 40.dp)
                .offset(x = fab1OffsetX, y = fab1OffsetY)
                .pointerInput(Unit){
                    detectTapGestures(
                        onLongPress = {
                            Toast.makeText(mContext, "Shopping Cart", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(Color.Green, shape = CircleShape)
                    .border(width = 1.dp, Color.Green, shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "fab1",
                    tint = Color.White
                )
            }
        }

        /**
         * Fab 2 Button
         **/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 32.dp, bottom = 40.dp)
                .offset(x = fab2OffsetX, y = fab2OffsetY)
                .pointerInput(Unit){
                    detectTapGestures(
                        onLongPress = {
                            Toast.makeText(mContext, "Email", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(Color.Red, shape = CircleShape)
                    .border(width = 1.dp, Color.Red, shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "fab2",
                    tint = Color.White
                )
            }
        }

        /**
         * Fab 3 Button
         **/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 32.dp, bottom = 40.dp)
                .offset(x = fab3OffsetX, y = fab3OffsetY)
                .pointerInput(Unit){
                    detectTapGestures(
                        onLongPress = {
                            Toast.makeText(mContext, "Contact", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(Color.Blue, shape = CircleShape)
                    .border(width = 1.dp, Color.Blue, shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "fab3",
                    tint = Color.White
                )
            }
        }

        /**
        * Main Fab Button
        **/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 32.dp, bottom = 40.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            IconButton(
                onClick = {
                    clickState = when (clickState) {
                        ClickState.Inactive -> ClickState.Active
                        ClickState.Active -> ClickState.Inactive
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .border(width = 1.dp, Color.DarkGray, shape = CircleShape),
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "mainFab",
                    tint = Color.White,
                    modifier = Modifier.rotate(mainFabRotate)
                )
            }
        }
    }
}