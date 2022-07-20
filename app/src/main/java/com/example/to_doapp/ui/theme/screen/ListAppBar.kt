package com.example.to_doapp.ui.theme.screen


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.ui.theme.topAppBackgroundColor
import com.example.to_doapp.ui.theme.topAppContentColor
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority
import androidx.compose.runtime.mutableStateOf as mutableStateOf
import androidx.compose.runtime.remember as remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.setValue
import com.example.to_doapp.components.PriorityItem
import kotlin.math.exp


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
    var expanded by remember { mutableStateOf(false)}
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_list)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.LOW)
                }
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.MEDIUM)
                }) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.HIGH)
                }) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}


@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar(
        onSearchClick = {}
    )
}