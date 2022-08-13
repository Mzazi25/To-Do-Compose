package com.example.to_doapp.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_doapp.navigation.destinations.listComposable
import com.example.to_doapp.navigation.destinations.splashComposable
import com.example.to_doapp.navigation.destinations.taskComposable
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Constants.LIST_SCREEN
import com.example.to_doapp.util.Constants.SPLASH_SCREEN

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController){
        Screens(navController)
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){

        splashComposable(
            navigateToListScreen = screen.splash)
        listComposable(
            sharedViewModel= sharedViewModel,
            navigateToTaskScreen = screen.list
        )
        taskComposable(
            sharedViewModel= sharedViewModel,
            navigateToListScreen = screen.task
        )
    }
}