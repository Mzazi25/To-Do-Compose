package com.example.to_doapp.data.models

import androidx.compose.ui.graphics.Color
import com.example.to_doapp.ui.theme.highPriority
import com.example.to_doapp.ui.theme.lowPriority
import com.example.to_doapp.ui.theme.mediumPriority
import com.example.to_doapp.ui.theme.nonePriority

enum class Priority(val color: Color) {
    HIGH(highPriority),
    MEDIUM(mediumPriority),
    LOW(lowPriority),
    NONE(nonePriority),
}