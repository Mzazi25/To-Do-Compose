package com.example.to_doapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.theme.screen.ListScreen
import com.example.to_doapp.util.Constants.LIST_ARGUMENTS
import com.example.to_doapp.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen : (taskId:Int) -> Unit
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENTS){
            type = NavType.StringType
        })
    ){
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}