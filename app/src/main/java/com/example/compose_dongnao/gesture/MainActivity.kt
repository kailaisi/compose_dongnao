package com.example.compose_dongnao.gesture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.gesture.ui.theme.Compose_dongnaoTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_dongnaoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Draggable2Sample()
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String) {
        var count by remember {
            mutableStateOf(0)
        }
        Text(text = "Hello $count!",
            modifier = Modifier
                .clickable {
                    Log.d(TAG, "onclick: ")
                    count++
                }
                .width(40.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { Log.d(TAG, "onPress: ") },
                        onTap = { Log.d(TAG, "onTap: ") },
                        onDoubleTap = { Log.d(TAG, "onDoubleTap: ") },
                        onLongPress = { Log.d(TAG, "onLongPress: ") },
                    )
                })
    }

    @Composable
    fun ScrollBoxes() {
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .size(100.dp)
                .verticalScroll(state)
        ) {
            repeat(100) {
                Text(text = "Item:$it", modifier = Modifier.padding(3.dp))
            }
        }
    }

    @Composable
    fun ScrollBoxesSmooth() {
        val state = rememberScrollState()
        LaunchedEffect(key1 = Unit) {
            state.animateScrollTo(100)
        }
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .size(100.dp)
                .verticalScroll(state)
        ) {
            repeat(100) {
                Text(text = "Item:$it", modifier = Modifier.padding(3.dp))
            }
        }
    }


    @Composable
    fun ScrollableSample() {
        var offset by remember {
            mutableStateOf(0f)
        }

        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .size(100.dp)
                .scrollable(orientation = Vertical,
                    state = rememberScrollableState { delta ->
                        offset += delta
                        delta
                    })
        ) {
            Text(
                text = "$offset", modifier = Modifier
                    .padding(3.dp)
                    .size(100.dp)
            )
        }
    }

    @Composable
    fun NestScrollableSample() {
        Box(
            Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ) {
            Column {
                repeat(6) {
                    Box(
                        Modifier
                            .height(120.dp)
                            .verticalScroll(rememberScrollState())
                    ) {


                        Text(
                            text = "Scroller",
                            Modifier
                                .border(12.dp, Companion.DarkGray)
                                .padding(24.dp)
                                .height(150.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Compose_dongnaoTheme {
            Greeting("Android")
        }
    }
}