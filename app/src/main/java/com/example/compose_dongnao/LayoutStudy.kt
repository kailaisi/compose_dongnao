package com.example.compose_dongnao

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayoutStudy() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Layout Study") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite, contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { padding ->
        BodyContent(modifier = Modifier.padding(padding))
    }

}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(6.dp)) {
        Text(text = "Hi this")
        Text(text = "Thanks for going through the LayoutStudy")
    }
}

