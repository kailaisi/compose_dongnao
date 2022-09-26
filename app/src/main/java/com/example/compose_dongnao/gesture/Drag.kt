package com.example.compose_dongnao.gesture

import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun DraggableSample() {
    var offset by remember {
        mutableStateOf(0f)
    }
    Text(text = "Drag ",
        modifier = Modifier
            .offset { IntOffset(offset.roundToInt(), 0) }
            .draggable(
                orientation = Horizontal,
                state = rememberDraggableState {
                    offset += it
                }
            ))
}

@Composable
fun Draggable2Sample() {
    var offsetx by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }
    Text(text = "Drag ",
        modifier = Modifier
            .offset { IntOffset(offsetx.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetx += dragAmount.x
                    offsetY += dragAmount.y
                }
            }

    )
}