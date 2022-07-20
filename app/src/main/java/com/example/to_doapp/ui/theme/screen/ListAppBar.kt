package com.example.to_doapp.ui.theme.screen

import android.icu.text.CaseMap
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListAppBar() {
    DefaultListAppBar()
}

@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar()
}