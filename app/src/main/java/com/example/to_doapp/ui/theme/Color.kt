package com.example.to_doapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val lightGrey = Color(0xFFFCFCFC)
val mediumGrey = Color(0xFF9C9C9C)
val darkGrey = Color(0xFF141414)


val lowPriority = Color(0xFF00C980)
val mediumPriority = Color(0xFFFFC114)
val highPriority = Color(0xFFFF4646)
val nonePriority = Color(0xFFFFFFFF)

val Colors.taskItemColor: Color
    @Composable
    get() = if (isLight) darkGrey else lightGrey

val Colors.taskIdBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.DarkGray

val Colors.topAppContentColor: Color
@Composable
get() = if (isLight) Color.White else lightGrey

val Colors.topAppBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.Black

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) Teal200 else Purple700
