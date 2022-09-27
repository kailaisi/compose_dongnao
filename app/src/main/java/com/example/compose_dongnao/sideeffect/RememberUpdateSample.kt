package com.example.compose_dongnao.sideeffect

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    val current by rememberUpdatedState(onTimeout)
    LaunchedEffect(key1 = Unit) {
        repeat(10) {
            delay(500)
            Log.d("RememberUpdatedState", "LandingScreen:  deley :${it}")
        }
        current()
    }
}

@Composable
fun RememberUpdatedStateSample() {
    val timeOut1: () -> Unit = { Log.d("RememberUpdatedState", "RememberUpdatedStateSample:  timeout1") }
    val timeOut2: () -> Unit = { Log.d("RememberUpdatedState", "RememberUpdatedStateSample:  timeout2") }
    val state = remember {
        mutableStateOf(timeOut1)
    }
    Column {
        Button(onClick = {
            if (state.value == timeOut1) {
                state.value = timeOut2
            } else {
                state.value = timeOut1
            }
        }) {
            Text(text = "choose TimeOut${if (state == timeOut1) 1 else 2}")
        }
        LandingScreen(state.value)
    }
}