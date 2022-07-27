package com.example.to_doapp.ui.theme.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.data.repository.ToDoRepository
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.Constants.MAX_TITLE_LENGTH
import com.example.to_doapp.util.RequestState
import com.example.to_doapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ToDoRepository):ViewModel() {

    val action:MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val description: MutableState<String> = mutableStateOf("")
    val title: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

     val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

     val searchText: MutableState<String> =mutableStateOf("")
   private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTask:StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    private val _searchTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val searchTask:StateFlow<RequestState<List<ToDoTask>>> = _searchTask

    fun searchDatabase(searchQuery: String){
        _searchTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.searchDatabase("%$searchQuery%")
                    .collect{ searchedTask ->
                        _searchTask.value = RequestState.Success(searchedTask)
                    }
            }
        } catch (e:Exception){
            _searchTask.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    fun getAllTask(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTask.collect {
                    _allTasks.value = RequestState.Success(it)
                }
        }

        } catch (e:Exception){
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask
    fun getSelectedTask(taskId: Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect{ task->
                _selectedTask.value = task
            }
        }
    }
    private fun addTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id=id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.updateTask(toDoTask)
        }
    }
    private fun deleteTask(){
        viewModelScope.launch (Dispatchers.IO){
            val toDoTask = ToDoTask(
                id=id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.deleteTask(toDoTask)
        }
    }
    private fun deleteAllTask(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllTask()
        }
    }

    fun handleDatabaseActions(action: Action){
        when(action){
            Action.ADD ->{
                addTask()
            }
            Action.UPDATE ->{
                updateTask()
            }
            Action.UNDO ->{
                addTask()
            }
            Action.DELETE ->{
                deleteTask()
            }
            Action.DELETE_ALL ->{
                deleteAllTask()

            }else ->{

            }
        }
        this.action.value = Action.NO_ACTION
    }

    fun updateTaskField(selectedTask: ToDoTask?){
        Log.d("Updated task Fields", selectedTask.toString())
        if(selectedTask != null){
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        }else{
            id.value = 0
            title.value =""
            description.value = ""
            priority.value = Priority.LOW
        }
    }
    fun updateTitle(updateTitle: String){
        if(updateTitle.length <MAX_TITLE_LENGTH){
            title.value = updateTitle
        }
    }
    fun validateFields(): Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}