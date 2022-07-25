package com.example.to_doapp.ui.theme.screen.tasks

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
                TaskAppBar(selectedTask=selectedTask,
                    navigateToListScreen =navigateToListScreen)
        },
        content = {
            TaskContent(
                title = "",
                onTitleChanged = {},
                priority = Priority.MEDIUM,
                onPriorityChanged = {},
                description ="" ,
                onDescriptionChanged = {}
            )
        }
    )
}