package com.example.to_doapp.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.*
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.RequestState
import com.example.to_doapp.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch as launch

@ExperimentalMaterialApi
@Composable
fun ListContent(
    allTasks:RequestState<List<ToDoTask>>,
    lowPriorityTask:List<ToDoTask>,
    highPriorityTask:List<ToDoTask>,
    sortState: RequestState<Priority>,
    onSwipeToDelete: (Action, ToDoTask)->Unit,
    searchedTask:RequestState<List<ToDoTask>>,
    navigateToTaskScreen:(taskId: Int) ->Unit,
    searchAppBarState: SearchAppBarState
)
{
    if (sortState is RequestState.Success){

        when{
            searchAppBarState ==SearchAppBarState.TRIGGERED ->{
                if(searchedTask is RequestState.Success){
                    HandleListContent(
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete= onSwipeToDelete,
                        task = searchedTask.data)
                }
            }
            sortState.data == Priority.NONE ->{
                if (allTasks is RequestState.Success){
                    HandleListContent(
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete= onSwipeToDelete,
                        task = allTasks.data
                    )
                }
        }
           sortState.data == Priority.LOW ->{
               HandleListContent(
                   navigateToTaskScreen = navigateToTaskScreen,
                   task = lowPriorityTask,
                   onSwipeToDelete= onSwipeToDelete,

                   )
           }

            sortState.data ==Priority.HIGH ->{
                HandleListContent(
                    navigateToTaskScreen = navigateToTaskScreen,
                    task = highPriorityTask,
                    onSwipeToDelete= onSwipeToDelete,
                )
            }
        }
    }

}
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    navigateToTaskScreen:(taskId: Int) ->Unit,
    onSwipeToDelete: (Action, ToDoTask)->Unit,
    task:List<ToDoTask>,
) {
    if(task.isEmpty()){
        EmptyContent()
    }else{
        DisplayTasks(
            tasks = task,
            onSwipeToDelete= onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks:List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask)->Unit,
    navigateToTaskScreen:(taskId: Int) ->Unit
) {
    LazyColumn(){
        items(
            items = tasks,
            key = { task->
                task.id
            }
        ){ task->
            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if(isDismissed && dismissDirection == DismissDirection.EndToStart){
                val scope = rememberCoroutineScope()
               scope.launch {
                   delay(300)
                   onSwipeToDelete(Action.DELETE,task)
                }
            }

            val degrees by animateFloatAsState(
                targetValue =
                if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                        -45f
            )
            var itemAppeared by remember { mutableStateOf(false)}

            LaunchedEffect(key1 = true ){
                itemAppeared = true
            }
            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            )
            {
                SwipeToDismiss(
                    state =dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {FractionalThreshold(fraction = 0.2f)},
                    background = {RedBackground(degrees = degrees)},
                    dismissContent = {
                        TaskItem(
                            toDoTask = task,
                            navigateToTaskScreen = navigateToTaskScreen
                        )
                    }
                )
            }
        }
    }
}
@ExperimentalMaterialApi
@Composable
fun TaskItem(
    toDoTask:ToDoTask,
    navigateToTaskScreen:(taskId: Int) ->Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.taskIdBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column (
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ){
            Row {
               Text(
                   modifier = Modifier.weight(8f),
                   text = toDoTask.title,
                   color = MaterialTheme.colors.taskItemColor,
                   style = MaterialTheme.typography.h5,
                   fontWeight = MaterialTheme.typography.h5.fontWeight,
                   maxLines = 1
               )
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                )
                {
                    Canvas(
                        modifier = Modifier
                            .height(PRIORITY_INDICATOR_SIZE)
                            .width(PRIORITY_INDICATOR_SIZE)
                    ){
                        drawCircle(
                            toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RedBackground(degrees:Float) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(highPriority)
            .padding(RED_BACKGROUND_PADDING),
        contentAlignment = Alignment.CenterEnd
            )
    {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector =Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PrevTaskId() {
    TaskItem(
        toDoTask = ToDoTask(
            id= 0,
            title = "Wagwan",
            description = "Hello There",
            priority = Priority.HIGH
        )
        , navigateToTaskScreen = {})
}