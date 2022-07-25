package com.example.to_doapp.navigation.destinations

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.theme.screen.ListScreen
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Constants.LIST_ARGUMENTS
import com.example.to_doapp.util.Constants.LIST_SCREEN
import com.example.to_doapp.util.toAction

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen : (taskId:Int) -> Unit
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENTS){
            type = NavType.StringType
        })
    ){ navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENTS).toAction()
       LaunchedEffect(key1 = action){
           sharedViewModel.action.value =action
       }
        ListScreen(
            sharedViewModel =sharedViewModel,
            navigateToTaskScreen = navigateToTaskScreen)
    }
}