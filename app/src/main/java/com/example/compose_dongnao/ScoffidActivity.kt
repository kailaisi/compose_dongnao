package com.example.compose_dongnao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose_dongnao.ui.theme.Compose_dongnaoTheme

class ScoffidActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_dongnaoTheme() {
                PhotographerCard()
            }
        }
    }

}



