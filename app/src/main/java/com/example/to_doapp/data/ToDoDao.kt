package com.example.to_doapp.data

import androidx.room.Dao
import androidx.room.Query
import com.example.to_doapp.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>
}