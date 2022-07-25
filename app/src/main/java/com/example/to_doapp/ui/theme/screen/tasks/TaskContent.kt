package com.example.to_doapp.ui.theme.screen.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.components.PriorityDropDown
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    priority: Priority,
    onPriorityChanged: (Priority) ->Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChanged(it) },
            label = { Text(text = stringResource(id = R.string.title_label))},
            singleLine = true,
            textStyle = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        PriorityDropDown(
            onPrioritySelected = onPriorityChanged,
            priority = priority
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = {onDescriptionChanged(it)},
            label = { Text(text = stringResource(id = R.string.description_label))},
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun PreviewTaskContent() {
    TaskContent(
        title = "",
        onTitleChanged ={} ,
        priority =Priority.HIGH,
        onPriorityChanged = {},
        description ="" ,
        onDescriptionChanged = {}
    )
}