package com.example.to_doapp.ui.theme.screen.tasks

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.to_doapp.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
                TaskAppBar(navigateToListScreen =navigateToListScreen)
        },
        content = {

        }
    )
}