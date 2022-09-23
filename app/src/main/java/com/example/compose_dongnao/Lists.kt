package com.example.compose_dongnao

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

// 不可滚动
@Composable
fun SimpleColumn() {
    Column {
        repeat(100) {
            Text(text = "Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

// 可滚动
@Composable
fun SimpleList() {
    val state = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state)
    ) {
        repeat(100) {
            Text(text = "Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

// 延迟加载
@Composable
fun LazyList() {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(100) {
            Text(text = "Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

// 延迟加载
@Composable
fun ScrollingList() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val listSize = 100
    Column {

        Row {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }) {
                Text(text = "Scroll Top")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(listSize - 1)
                    }
                }) {
                Text(text = "Scroll Bottom")
            }
        }


        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20200121%2F26f3609e0a8f42b09476448c93bd1e96.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1666507093&t=b91bf923c27b39af1fffee9d0e445ae1"),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}
