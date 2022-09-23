package com.example.compose_dongnao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.ui.theme.Compose_dongnaoTheme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_dongnaoTheme() {
                Conversation(SampleData.conversationSample)
            }
        }
    }

    @Composable
    fun MessageCard(message: Message) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.picDes),
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
            )
            var isExpanded by remember {
                mutableStateOf(false)
            }
            val surfaceColor: Color by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(text = "Hello ${message.author}!", color = MaterialTheme.colors.secondaryVariant)
                Spacer(modifier = Modifier.height(40.dp))
                Surface(
                    color = surfaceColor,
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = "Hello ${message.body}!",
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn() {
            items(messages) { message ->
                MessageCard(message = message)
            }
        }
    }

    @Preview
    @Composable
    fun MessageCard() {
        MessageCard(message = Message("c", "SDSD"))
    }
}



