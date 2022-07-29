package com.example.to_doapp.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.to_doapp.ui.theme.screen.splash.SplashScreen
import com.example.to_doapp.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
    navigateToListScreen : () ->Unit
){
    composable(
        route = SPLASH_SCREEN
    ){
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}