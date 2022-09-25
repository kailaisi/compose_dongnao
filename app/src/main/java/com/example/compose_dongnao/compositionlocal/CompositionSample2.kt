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
fun CompositionSample3() {
    Column {
        CompositionLocalProvider(LocalElevation provides CardElevation.high ) {
            MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

            }
        }

        MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

        }
    }
}
