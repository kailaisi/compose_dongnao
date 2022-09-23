package com.example.compose_dongnao.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    fun addItem(todoItem: TodoItem) {
        _todoItems.postValue(_todoItems.value!! + listOf(todoItem))
    }

    fun removeItem(todoItem: TodoItem) {
        _todoItems.postValue(_todoItems.value!!.filter { it != todoItem }.toMutableList())
    }
}