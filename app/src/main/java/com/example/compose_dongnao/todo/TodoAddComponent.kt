package com.example.compose_dongnao.todo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.compose_dongnao.todo.TodoIcon.Companion

@Composable
fun AddTodoComponent(onAddClick: (TodoItem) -> Unit) {
    Column {
        val (text, setText) = remember { mutableStateOf("") }
        val (icon, setIcon) = remember {
            mutableStateOf(TodoIcon.Default)
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChanged = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            TodoAddButton(
                "Add",
                onClick = {
                    onAddClick(TodoItem(text, icon))
                    setText("")
                },
                enable = text.isNotBlank(),
                modifier = Modifier.align(CenterVertically)
            )
        }
        IconRow(visible = text.isNotBlank(), icon = icon, onItemClick = setIcon)

    }
}

@Composable
fun IconRow(modifier: Modifier = Modifier, visible: Boolean, icon: TodoIcon, onItemClick: (TodoIcon) -> Unit) {
    val enter = remember { fadeIn(animationSpec = TweenSpec(500)) }
    val out = remember { fadeOut(animationSpec = TweenSpec(100)) }
    Box(modifier = modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(
            visible = visible,
            modifier = modifier,
            enter = enter,
            exit = out
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                TodoIcon.values().forEach {
                    SelectIcon(icon = it.vectorAsset, onIconClick = onItemClick, isSelect = it == icon)
                }
            }
        }
    }
}

@Composable
fun SelectIcon(icon: ImageVector, onIconClick: (TodoIcon) -> Unit, isSelect: Boolean) {
    Icon(imageVector = icon, contentDescription = null，)
}

@Composable
fun TodoInputText(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
    )
}

@Composable
fun TodoAddButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit, enable: Boolean) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        enabled = enable,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = text)
    }
}