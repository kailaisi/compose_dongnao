package com.example.compose_dongnao.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.todo.utils.generateRandomTodoItem
import kotlin.random.Random

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    Column {
        AddTodoComponent(onAddClick = onAddItem)
        LazyColumn(modifier = Modifier.weight(1f), contentPadding = PaddingValues(8.dp)) {
            items(items) {
                ToDoRow(
                    todoItem = it,
                    modifier = Modifier.fillParentMaxWidth(),
                    onItemClick = onRemoveItem
                )
            }
        }
        Button(
            onClick = {
                onAddItem(generateRandomTodoItem())
            }, modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Add Random Item")
        }
    }
}

@Composable
fun ToDoRow(
    modifier: Modifier = Modifier, todoItem: TodoItem,
    onItemClick: (TodoItem) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onItemClick(todoItem)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = todoItem.task)
        val alpha = remember(todoItem.id) {
            randomTint()
        }
        Icon(
            imageVector = todoItem.icon.vectorAsset, contentDescription = null,
            tint = LocalContentColor.current.copy(alpha = alpha)
        )
    }
}

private fun randomTint() = Random.nextFloat().coerceIn(0.3f, 0.9f)
