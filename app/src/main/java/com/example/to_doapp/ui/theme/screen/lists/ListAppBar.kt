package com.example.to_doapp.ui.theme.screen


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.ui.theme.topAppBackgroundColor
import com.example.to_doapp.ui.theme.topAppContentColor
import com.example.to_doapp.R
import com.example.to_doapp.data.models.Priority
import androidx.compose.runtime.mutableStateOf as mutableStateOf
import androidx.compose.runtime.remember as remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis.Companion.All
import androidx.compose.ui.text.input.DeleteAllCommand
import androidx.compose.ui.text.input.ImeAction
import com.example.to_doapp.components.PriorityItem
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.TOP_BAR_HEIGHT
import com.example.to_doapp.ui.theme.viewModels.SharedViewModel
import com.example.to_doapp.util.SearchAppBarState
import com.example.to_doapp.util.TrailingIconState
import kotlin.math.exp


@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchText:String
) {
    when(searchAppBarState){
        SearchAppBarState.CLOSED ->{
            DefaultListAppBar(
                onSearchClick = {
                                sharedViewModel.searchAppBarState.value =SearchAppBarState.OPENED
                },
                onSortPriority = {},
                onDeleteAll = {}
            )
        }
        else ->{
            SearchAppBar(
                text = searchText,
                onClosedClicked = {
                                  sharedViewModel.searchAppBarState.value =
                                      SearchAppBarState.CLOSED
                                    sharedViewModel.searchText.value= ""
                },
                onSearchedClicked = {
                                    sharedViewModel.searchDatabase(it)
                },
                onTextChanged = { newText->
                    sharedViewModel.searchText.value = newText
                }
            )
        }
    }

}

@Composable
fun DefaultListAppBar(
    onSearchClick : () -> Unit,
    onSortPriority: (Priority) -> Unit,
    onDeleteAll: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.tasks),
                color = MaterialTheme.colors.topAppContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBackgroundColor,
        actions = {
            ListAppBarActions(onSearchClick, onSortPriority,onDeleteAll)
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClick : () -> Unit,
    onSortPriority: (Priority) -> Unit,
    onDeleteAll: () -> Unit

) {
    searchAction(onSearchClick = onSearchClick)
    SortAction(onSortPriority = onSortPriority)
    DeleteAll(onDeleteAll)
}

@Composable
fun searchAction(
    onSearchClick : () -> Unit
) {
    IconButton(
        onClick = {onSearchClick()}) {
        Icon(
            imageVector= Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search_task),
            tint = MaterialTheme.colors.topAppContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortPriority: (Priority) ->Unit
) {
    var expanded by remember { mutableStateOf(false)}
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_list),
            tint = MaterialTheme.colors.topAppContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.LOW)
                }
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.MEDIUM)
                }) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortPriority(Priority.HIGH)
                }) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Composable
fun DeleteAll(
    onDeleteAll: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = stringResource(id = R.string.delete_all),
            tint = MaterialTheme.colors.topAppContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    onDeleteAll()
                    expanded = false
                }){
                Text(
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    text = stringResource(id = R.string.delete_all),
                    style = MaterialTheme.typography.subtitle2
                )
            }


        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChanged:(String) ->Unit,
    onClosedClicked: () ->Unit,
    onSearchedClicked:(String) -> Unit
) {
    var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_DELETE)}
    Surface(
        modifier = Modifier
            .height(TOP_BAR_HEIGHT)
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange ={
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.search_widget),
                    color = Color.White
                )
            },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
               color = MaterialTheme.colors.topAppContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_task),
                        tint = MaterialTheme.colors.topAppContentColor
                    )
                }

            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when(trailingIconState){
                            TrailingIconState.READY_TO_DELETE ->{
                                onTextChanged("")
                                trailingIconState=TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE ->{
                                if(text.isNotEmpty()){
                                    onTextChanged("")
                                }else {
                                    onClosedClicked()
                                    trailingIconState =TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.close_search_bar),
                        tint = MaterialTheme.colors.topAppContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                onSearchedClicked(text)
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}
@Preview
@Composable
fun DefaultTopBarPreview() {
    DefaultListAppBar(
        onSearchClick = {},
        onSortPriority = {},
        onDeleteAll = {}
    )
}