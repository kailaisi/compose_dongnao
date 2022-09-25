package com.example.compose_dongnao.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.todo.utils.generateRandomTodoItem
import kotlin.random.Random

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,
    currentEdit: TodoItem?,
    onStartEdit: (TodoItem) -> Unit,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: (TodoItem) -> Unit,
) {
    val isEdit = currentEdit != null
    Column {
        TodoInputBackground(elevate = true) {
            if (isEdit) {
                Text(
                    text = "Editing",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
            } else {
                TodoItemEntryInput(onAddClick = onAddItem)
            }
        }
        LazyColumn(modifier = Modifier.weight(1f), contentPadding = PaddingValues(8.dp)) {
            items(items) {
                if (it.task != currentEdit?.task) {
                    ToDoRow(
                        todoItem = it,
                        modifier = Modifier.fillParentMaxWidth(),
                        onItemClick = onStartEdit
                    )
                } else {
                    TodoItemEditInline(todoItem = it,
                        onEditChanged = onEditItemChange,
                        onEditDone = { onEditDone(it) },
                        onRemoveItem = { onRemoveItem(it) })
                }
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
