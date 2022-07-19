package com.example.to_doapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_doapp.navigation.destinations.listComposable
import com.example.to_doapp.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {
    val screen = remember(navController){
        Screens(navController)
    }
    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ){
        listComposable(
            navigateToTaskScreen = screen.task
        )
    }
}