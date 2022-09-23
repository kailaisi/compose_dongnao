package com.example.compose_dongnao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android","Jet Compose"))
        }
    }
    @Composable
    fun MessageCard(message: Message) {
        Text(text = "Hello ${message.author}!")
        Text(text = "Hello ${message.body}!")
    }

    data class Message(val author:String,val body:String)
}



