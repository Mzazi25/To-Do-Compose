package com.example.to_doapp.ui.theme.screen.tasks

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority


    Scaffold(
        topBar = {
                TaskAppBar(selectedTask=selectedTask,
                    navigateToListScreen =navigateToListScreen)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChanged = {
                    sharedViewModel.title.value = it
                },
                priority = priority,
                onPriorityChanged = {
                    sharedViewModel.priority.value = it
                },
                description =description ,
                onDescriptionChanged = {
                    sharedViewModel.description.value = it
                }
            )
        }
    )
}