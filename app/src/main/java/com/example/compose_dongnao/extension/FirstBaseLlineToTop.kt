package com.example.compose_dongnao.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

// 自定义布局，设置基线的距离
fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val firstBaseLine = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
        val height = placeable.height + placeableY
        val width = placeable.width
        layout(width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)