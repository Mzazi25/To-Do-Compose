package com.example.to_doapp.ui.theme.screen

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R

@Composable
fun ListScreen(navigateToTaskScreen : (Int) -> Unit) {

    Scaffold(
        topBar = {
            ListAppBar()
        },
        content = {},
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreen : (Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreen(-1)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_task) )
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}