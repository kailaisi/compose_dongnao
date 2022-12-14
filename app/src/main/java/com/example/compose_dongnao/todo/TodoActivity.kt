package com.example.compose_dongnao.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose_dongnao.todo.ui.theme.Compose_JetpackComposeTheme

class TodoActivity : ComponentActivity() {
    private val viewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    TodoActivityScreen()
                }
            }
        }
    }

    @Composable
    private fun TodoActivityScreen() {
        val items =
            TodoScreen(
                items = viewModel.todoItems,
                onAddItem = { viewModel.addItem(it) },
                onRemoveItem = { viewModel.removeItem(it) },
                onEditDone = { viewModel.onEditDone() },
                currentEdit = viewModel.currentEditItem,
                onEditItemChange = viewModel::onEditItemChange,
                onStartEdit = viewModel::onEditSelect
            )
    }
}
