package com.example.compose_dongnao.extension

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.ui.theme.Compose_dongnaoTheme

@Composable
fun MyColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }
        var yPos = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach {
                it.placeRelative(0, yPos)
                yPos += it.height
            }
        }
    }
}

@Composable
fun MyColumnSample() {
    Compose_dongnaoTheme {
        MyColumn(Modifier.padding(10.dp)) {
            Text(text = "sdfsdf")
            Text(text = "sdfsdf")
            Text(text = "sdfsdf")
            Text(text = "sdfsdf")
        }
    }
}