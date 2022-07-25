package com.example.to_doapp.ui.theme.screen.tasks

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
    val context = LocalContext.current
    Scaffold(
        topBar = {
                TaskAppBar(selectedTask=selectedTask,
                    navigateToListScreen ={ action->
                        if (action== Action.NO_ACTION){
                            navigateToListScreen(action)
                        }else{
                            if (sharedViewModel.validateFields()){
                                navigateToListScreen(action)
                            }else{
                                displayToast(context = context)
                            }
                        }
                    })
        },
        content = {
            TaskContent(
                title = title,
                onTitleChanged = {
                    sharedViewModel.updateTitle(it)
                },
                priority = priority,
                onPriorityChanged = {
                    sharedViewModel.priority.value = it
                },
                description =description ,
                onDescriptionChanged = {
                    sharedViewModel.description.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty",
        Toast.LENGTH_SHORT
    ).show()
}
