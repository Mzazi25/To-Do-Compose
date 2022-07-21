package com.example.to_doapp.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.TASK_ELEVATION
import com.example.to_doapp.ui.theme.taskIdBackgroundColor

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

        }
    }
}