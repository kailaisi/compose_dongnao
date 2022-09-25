package com.example.compose_dongnao.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.example.compose_dongnao.R

@Composable
fun CompositionSample() {
    MaterialTheme {
        Column {
            Text("line1")

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.medium,
            ) {
                Text("line2")
                Text("line3")
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.disabled,
                ) {
                    FruitText(1)
                    FruitText(2)
                }
            }
        }
    }
}

@Composable
fun FruitText(size: Int) {
    val resources = LocalContext.current.resources
    val quantityString = resources.getQuantityString(R.plurals.fruit_title, size)
    Text(text = quantityString)
}