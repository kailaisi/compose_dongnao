package com.example.compose_dongnao.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    var todoItems = mutableStateListOf<TodoItem>()
        private set

    private var currentEditPosition by mutableStateOf(-1)

    val currentEditItem get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(todoItem: TodoItem) {
        todoItems.add(todoItem)
    }

    fun removeItem(todoItem: TodoItem) {
        todoItems.remove(todoItem)
        onEditDone()
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditSelect(todoItem: TodoItem) {
        currentEditPosition = todoItems.indexOf(todoItem)
    }

    // 编辑过程中重新赋值
    fun onEditItemChange(todoItem: TodoItem) {
        todoItems[currentEditPosition] = todoItem
    }
}