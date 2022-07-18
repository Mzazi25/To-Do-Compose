package com.example.to_doapp.data.repository

import com.example.to_doapp.data.ToDoDao
import com.example.to_doapp.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {
    val getAllTask: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask>{
        return toDoDao.getSelectedTask(taskId)
    }

    suspend fun addTask(toDoTask:ToDoTask){
        toDoDao.addTask(toDoTask)
    }
    suspend fun updateTask(toDoTask: ToDoTask){
        toDoDao.updateTask(toDoTask)
    }

    suspend fun deleteAllTask(){
        toDoDao.deleteAllTasks()
    }

    fun searchQuery(searchQuery:String): Flow<List<ToDoTask>>{
        return toDoDao.searchDataBase(searchQuery)
    }
}