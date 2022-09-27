package com.example.compose_dongnao.sideeffect

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun BackHandler(dispatcher: OnBackPressedDispatcher, onBack: () -> Unit) {
   val backClick by  rememberUpdatedState(newValue = onBack)
    val backCallBack = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backClick()
            }
        }
    }
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(backCallBack)
        onDispose {
            backCallBack.remove()
        }
    }
}

@Composable
fun DisposeSample(dispatcher: OnBackPressedDispatcher) {
    var callback by remember {
        mutableStateOf(false)
    }
    Row {
        Switch(checked = callback, onCheckedChange = {
            callback = !callback
        })
        Text(text = if (callback) "add call back" else "not add call back")
    }
    if (callback) {
        BackHandler(dispatcher) {
            Log.d("DisposeSample", "DisposeSample: ")
        }
    }
}