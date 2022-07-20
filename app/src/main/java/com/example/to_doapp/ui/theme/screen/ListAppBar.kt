package com.example.to_doapp.ui.theme.screen

import android.icu.text.CaseMap
import android.service.autofill.OnClickAction
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.ui.theme.topAppBackgroundColor
import com.example.to_doapp.ui.theme.topAppContentColor
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority

@Composable
fun ListAppBar() {
    DefaultListAppBar{

    }
}

@Composable
fun DefaultListAppBar(onSearchClick : () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colors.topAppContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor,
        actions = {
            ListAppBarActions(onSearchClick)
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
        onClick = {onSearchClick}) {
        Icon(
            imageVector= Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search_task),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortPriority: (Priority) ->Unit
) {

}

@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar(
        onSearchClick = {}
    )
}