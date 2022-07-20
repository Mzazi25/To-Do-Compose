package com.example.to_doapp.ui.theme.screen

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListScreen(navigateToTaskScreen : (Int) -> Unit) {

    Scaffold(
        content = {},
        floatingActionButton = {
            ListFab()
        }
    )
}

@Composable
fun ListFab() {
    FloatingActionButton(
        onClick = { }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Task" )
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}