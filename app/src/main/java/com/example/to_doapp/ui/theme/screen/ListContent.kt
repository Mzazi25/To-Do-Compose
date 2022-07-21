package com.example.to_doapp.ui.theme.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.*

@Composable
fun ListContent() {

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