package com.example.to_doapp.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.ui.theme.BORDER_WIDTH
import com.example.to_doapp.ui.theme.PRIORITY_DROP_DOWN

@Composable
fun PriorityDropDown(
    onPrioritySelected: (Priority) -> Unit,
    priority: Priority
) {
    var expanded by remember{
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN)
            .clickable { expanded = true }
            .border(
                width = BORDER_WIDTH,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
            )
    ) {

    }
}