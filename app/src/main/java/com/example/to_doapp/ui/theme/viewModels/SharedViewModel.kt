package com.example.to_doapp.ui.theme.viewModels

import androidx.lifecycle.ViewModel
import com.example.to_doapp.data.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ToDoRepository):ViewModel() {

}