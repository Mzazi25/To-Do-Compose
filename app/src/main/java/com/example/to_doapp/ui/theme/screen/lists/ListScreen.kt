package com.example.to_doapp.ui.theme.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
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
        sharedViewModel.readSortState()
    }
    val action by sharedViewModel.action
    val allTasks by sharedViewModel.allTask.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.sortLowPriority.collectAsState()
    val highPriorityTasks by sharedViewModel.sortHighPriority.collectAsState()
    val searchedTask by sharedViewModel.searchTask.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchText:String by sharedViewModel.searchText
    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        action =action,
        onUndoClicked ={
                       sharedViewModel.action.value = it
        },
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
                allTasks = allTasks,
                navigateToTaskScreen= navigateToTaskScreen,
                searchAppBarState = searchAppBarState,
                searchedTask = searchedTask,
                lowPriorityTask = lowPriorityTasks,
                sortState = sortState,
                highPriorityTask = highPriorityTasks,
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
    onUndoClicked: (Action) -> Unit,
    handleDatabaseAction: () -> Unit,
    taskTitle: String
) {
    handleDatabaseAction()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 =action){
        if (action != Action.NO_ACTION){
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = snackBarMessage(action,taskTitle),
                    actionLabel = setActionLabel(action)
                )
                undoDeletedTask(
                    action = Action.DELETE,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun snackBarMessage(action: Action, taskTitle:String): String{
    return when(action){
        Action.DELETE_ALL -> "All Tasks Removed"
        else -> "${action.name}: $taskTitle"
    }

}

private fun setActionLabel(action: Action): String{
    return if(action.name == "DELETE"){
        "UNDO"
    }else{
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
){
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE){
       onUndoClicked(Action.UNDO)
    }
}




















