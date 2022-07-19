package com.example.to_doapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Int) -> Unit
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENTS){
            type = NavType.StringType
        })
    ){

    }
}