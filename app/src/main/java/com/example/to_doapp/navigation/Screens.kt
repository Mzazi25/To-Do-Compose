package com.example.to_doapp.navigation

import androidx.navigation.NavHostController
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.Constants.LIST_SCREEN
import com.example.to_doapp.util.Constants.TASK_SCREEN

class Screens(navController:NavHostController) {
    val list: (Action)-> Unit = { action ->
        navController.navigate("List/${action.name}") {
            popUpTo(LIST_SCREEN)
        }
    }
    val task:(Int) -> Unit = { taskId ->
        navController.navigate("Task/${taskId}")
    }
}