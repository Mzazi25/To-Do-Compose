package com.example.to_doapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)){
            drawCircle(priority.color)
        }
        Spacer(modifier = Modifier.width(LARGE_PADDING))
        Text(
            text = priority.name,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview
@Composable
fun PreviewPriority() {
    PriorityItem(priority = Priority.LOW)
}