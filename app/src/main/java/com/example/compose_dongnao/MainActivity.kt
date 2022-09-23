package com.example.compose_dongnao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard("My Android")
        }
    }
    @Composable
    fun MessageCard(name: String) {
        Text(text = "Hello $name!")
    }
}



