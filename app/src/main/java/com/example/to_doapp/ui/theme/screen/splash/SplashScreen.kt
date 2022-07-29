package com.example.to_doapp.ui.theme.screen.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.ui.theme.IMAGE_PADDING
import com.example.to_doapp.ui.theme.ToDoAppTheme
import com.example.to_doapp.ui.theme.splashScreen

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashScreen),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(IMAGE_PADDING),
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(id = R.string.splash_screen_logo)
        )
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.ic_logo_dark
    } else {
        R.drawable.ic_logo_light
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

@Preview
@Composable
fun SplashScreenPreview2() {
    ToDoAppTheme(darkTheme = true) {
        SplashScreen()
    }
}