package com.example.to_doapp.navigation

import androidx.navigation.NavHostController
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.Constants.LIST_SCREEN
import com.example.to_doapp.util.Constants.SPLASH_SCREEN
import com.example.to_doapp.util.Constants.TASK_SCREEN

class Screens(navController:NavHostController){

    val splash:()-> Unit = {
        navController.navigate("list/${Action.NO_ACTION.name}"){
            popUpTo(SPLASH_SCREEN){ inclusive = true}
        }
    }
    val task: (Action)-> Unit = { action ->
        navController.navigate("List/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }
    val list:(Int) -> Unit = { taskId ->
        navController.navigate("Task/${taskId}")
    }
}