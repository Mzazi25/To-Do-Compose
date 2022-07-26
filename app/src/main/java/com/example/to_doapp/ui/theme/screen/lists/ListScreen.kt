package com.example.to_doapp.ui.theme.screen

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.ui.theme.fabBackgroundColor
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen : (taskId:Int) -> Unit) {

    LaunchedEffect(key1 =true){
        sharedViewModel.getAllTask()
    }
    val action by sharedViewModel.action
    val allTasks by sharedViewModel.allTask.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchText:String by sharedViewModel.searchText
    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        action =action,
        handleDatabaseAction = { sharedViewModel.handleDatabaseActions(action) },
        taskTitle = sharedViewModel.title.value
    )
    
    Scaffold(
        scaffoldState= scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState =searchAppBarState,
                searchText =searchText
            )
        },
        content = {
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen= navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(navigateToTaskScreen : (taskId:Int) -> Unit) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.fabBackgroundColor,
        onClick = {
            navigateToTaskScreen(-1)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_task) )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    action: Action,
    handleDatabaseAction: () -> Unit,
    taskTitle: String
) {
    handleDatabaseAction()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 =action){
        if (action != Action.NO_ACTION){
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "Ok"
                )
            }
        }
    }
}






















