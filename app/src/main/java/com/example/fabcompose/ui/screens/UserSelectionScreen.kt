package com.example.fabcompose.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.fabcompose.ui.screens.destinations.FabAnimLayoutDestination
import com.example.fabcompose.utils.Users
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun UserSelectionLayout(
    navigator: DestinationsNavigator
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var userType by remember { mutableStateOf(Users.None) }
        val transition = updateTransition(targetState = userType, label = "userType")

        val emplAlpha by transition.animateFloat(label = "userType") { user ->
            when (user) {
                Users.None -> 1f
                Users.Employee,
                Users.Citizen -> 0f
            }
        }
        val ctznAlpha by transition.animateFloat(label = "userType") { user ->
            when (user) {
                Users.None -> 1f
                Users.Citizen,
                Users.Employee -> 0f
            }
        }
        val continueAlpha by transition.animateFloat(label = "userType") { user ->
            when (user) {
                Users.None -> 0f
                Users.Citizen,
                Users.Employee -> 1f
            }
        }

        val emplBoxHeight by transition.animateFloat(label = "userType") { user ->
            when (user) {
                Users.None -> 0.6f
                Users.Employee -> 1f
                Users.Citizen -> 0f
            }
        }
        val emplBoxWidth by transition.animateFloat(label = "userType") { user ->
            when (user) {
                Users.None,
                Users.Employee -> 1f
                Users.Citizen -> 0f
            }
        }

        val boxColor by transition.animateColor(label = "userType") { user ->
            when (user) {
                Users.None,
                Users.Citizen -> MaterialTheme.colorScheme.primary
                Users.Employee -> MaterialTheme.colorScheme.secondary
            }
        }

        val employeeAreaShape = GenericShape { size, _ ->
            moveTo(0f, 0f) //Set starting point
            lineTo(0f, size.height) //Draw line to a point
            lineTo(size.width, size.height / 1.4f)
            lineTo(size.width, 0f)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(boxColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight(emplBoxHeight)
                    .fillMaxWidth(emplBoxWidth)
                    .graphicsLayer {
                        shape = employeeAreaShape
                        clip = true
                    }
                    .background(MaterialTheme.colorScheme.secondary)
                    .alpha(emplAlpha),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        userType = Users.Employee
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                        .alpha(emplAlpha)
                ) {
                    Row {
                        Text(
                            text = "EMPLOYEE LOGIN",
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 5.dp)
                        )
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "employeeBtn",
                            tint = Color.White
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-50).dp)
                    .alpha(ctznAlpha),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        userType = Users.Citizen
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                        .alpha(ctznAlpha)
                ) {
                    Row {
                        Text(
                            text = "CITIZEN LOGIN",
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 5.dp)
                        )
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "citizenBtn",
                            tint = Color.White
                        )
                    }
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            if (userType == Users.Employee || userType == Users.Citizen){
                IconButton(
                    onClick = {
                        navigator.navigate(FabAnimLayoutDestination)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                        .alpha(continueAlpha)
                ) {
                    Row {
                        Text(
                            text = "Continue",
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 5.dp)
                        )
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "continueBtn",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}