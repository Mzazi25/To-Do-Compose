package com.example.to_doapp.ui.theme.screen

import android.icu.text.CaseMap
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.ui.theme.topAppBackgroundColor
import com.example.to_doapp.ui.theme.topAppContentColor

@Composable
fun ListAppBar() {
    DefaultListAppBar()
}

@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colors.topAppContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor
    )
}

@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar()
}