package com.example.to_doapp.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.to_doapp.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog:Boolean,
    onCloseClick: () -> Unit,
    onYesClicked: () -> Unit
) {
    if(openDialog){
        AlertDialog(
            title= {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                   Text(
                       text = message,
                       fontWeight = FontWeight.Normal,
                       fontStyle = MaterialTheme.typography.subtitle1.fontStyle
                   )
            },
            confirmButton = {
                            Button(
                                onClick = {
                                    onYesClicked()
                                    onCloseClick() })
                            {
                                Text(text = stringResource(id = R.string.on_yes_click))
                            }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onCloseClick() })
                {
                    Text(text = stringResource(R.string.on_no_click))
                }
            },
            onDismissRequest = { onCloseClick() }
        )
    }
}