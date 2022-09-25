package com.example.compose_dongnao.compositionlocal

import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevations(val card: Dp = 0.dp)

object CardElevation {
    val high get() = Elevations(card = 10.dp)
    val low get() = Elevations(card = 5.dp)
}

// 创建得到local的对象
val LocalElevation = compositionLocalOf { Elevations() }


@Composable
fun MyCard(
    elevation: Dp = LocalElevation.current.card,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        elevation = elevation,
        backgroundColor = backgroundColor,
        modifier = Modifier.size(200.dp),
        content = content,
    )
}