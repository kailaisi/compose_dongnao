package com.example.compose_dongnao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android","Jet Compose"))
        }
    }
    
    @Composable
    fun MessageCard(message: Message) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.picDes),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(text = "Hello ${message.author}!")
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Hello ${message.body}!")
            }
        }
    }

    data class Message(val author:String,val body:String)


    @Preview
    @Composable
    fun MessageCard() {
        MessageCard(message = Message("c", "SDSD"))
    }
}



