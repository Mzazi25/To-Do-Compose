package com.example.to_doapp.ui.theme.screen.tasks

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.topAppBackgroundColor
import com.example.to_doapp.ui.theme.topAppContentColor
import com.example.to_doapp.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask ==null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }else{
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}


@Composable
fun BackAction(
    onBackClicked: (Action) ->Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.go_back_arrow),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) ->Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    selectedTask:ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
                         CloseAction(onClosedClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor,
        actions = {
            UpdateAction(onUpdateAction = navigateToListScreen)
            DeleteAction(onDeleteAction = navigateToListScreen)
        }
    )
}
@Composable
fun CloseAction(
    onClosedClicked: (Action) -> Unit
) {
    IconButton(onClick = { onClosedClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteAction: (Action) -> Unit
) {
    IconButton(onClick = { onDeleteAction(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_task),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateAction: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateAction(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_task),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}


@Preview
@Composable
fun PreviewTaskBar() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Preview
@Composable
fun PreviewAppTaskBar() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 0,
            title = "Caleb Mzazi",
            description = "Hello World",
            priority = Priority.HIGH
        ),
        navigateToListScreen ={} )
}