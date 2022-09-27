package com.example.compose_dongnao.sideeffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ScaffoldSample(state: MutableState<Boolean>, scaffoldState: ScaffoldState = rememberScaffoldState()) {
    if (state.value) {
        LaunchedEffect(state.value) {
            scaffoldState.snackbarHostState.showSnackbar("Error Message", actionLabel = "Retry message")
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "我是副效应") })
        },
        content = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    state.value = !state.value
                }) {
                    Text(text = "弹窗")
                }
            }
        })
}

@Composable
fun LaunchScaffold() {
    val state = remember {
        mutableStateOf(false)
    }
    MoviesScreen()
}


@Composable
fun MoviesScreen() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "我是副效应") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        drawerContent = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("抽屉中的组建")
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("我是snakebar", "test")
                    }
                }) {
                    Text(text = "弹窗")
                }
            }
        })
}