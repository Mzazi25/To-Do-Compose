package com.example.to_doapp.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.theme.screen.tasks.TaskScreen
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.Constants
import com.example.to_doapp.util.Constants.TASK_ARGUMENTS

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen : (Action) -> Unit
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENTS){
            type = NavType.IntType
        })
    ){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENTS)
        sharedViewModel.getSelectedTask(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask){
            sharedViewModel.updateTaskField(selectedTask =selectedTask)
        }

        TaskScreen(selectedTask=selectedTask,
            navigateToListScreen = navigateToListScreen,sharedViewModel)
    }
}