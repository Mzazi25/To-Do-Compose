package com.example.to_doapp.ui.theme.screen

import android.icu.text.CaseMap
import android.service.autofill.OnClickAction
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor,
        actions = {

        }
    )
}

@Composable
fun ListAppBarActions( onSearchClick : () -> Unit) {
    searchAction(onSearchClick = onSearchClick)
}

@Composable
fun searchAction(
    onSearchClick : () -> Unit
) {
    IconButton(
        onClick = { onSearchClick}) {
        Icon(
            imageVector= Icons.Default.Search,
            contentDescription = "Search Tasks",
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar()
}