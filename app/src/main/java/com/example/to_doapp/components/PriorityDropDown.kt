package com.example.to_doapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.ui.theme.BORDER_WIDTH
import com.example.to_doapp.ui.theme.PRIORITY_DROP_DOWN
import com.example.to_doapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.to_doapp.ui.theme.lowPriority

@Composable
fun PriorityDropDown(
    onPrioritySelected: (Priority) -> Unit,
    priority: Priority
) {
    var expanded by remember{
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if(expanded)180f else 0f )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(PRIORITY_DROP_DOWN)
            .clickable { expanded = true }
            .border(
                width = BORDER_WIDTH,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            )
    ) {
        Canvas(
            modifier = Modifier
                .weight(1f)
                .size(PRIORITY_INDICATOR_SIZE))
        {
            drawCircle(color = priority.color)
        }
        Text(
            modifier =Modifier.weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2
        )
        IconButton(
            modifier = Modifier
                .rotate(angle)
                .weight(1.5f)
                .alpha(ContentAlpha.medium),
            onClick = {expanded = true})
        {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.arrow_drop_down)
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94f),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                })
            {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                })
            {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                })
            {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Preview
@Composable
fun PrevPriority() {
    PriorityDropDown(
        onPrioritySelected = {},
        priority = Priority.HIGH
    )
}