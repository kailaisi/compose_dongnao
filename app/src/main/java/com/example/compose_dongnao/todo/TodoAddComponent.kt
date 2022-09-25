package com.example.compose_dongnao.todo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// 编辑
@Composable
fun TodoItemEditInline(
    todoItem: TodoItem,
    onEditChanged: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
) {
    val submit = {}
    TodoItemInput(
        text = todoItem.task,
        icon = todoItem.icon,
        onSubmit = submit,
        onIconChanged = { onEditChanged(todoItem.copy(icon = it)) },
        onTextChanged = { onEditChanged(todoItem.copy(task = it)) }) {
        Row {
            TextButton(onClick = onEditDone) {
                Text(text = "确认", textAlign = TextAlign.Center, modifier = Modifier.width(30.dp))
            }
            TextButton(onClick = onRemoveItem) {
                Text(text = "删除", textAlign = TextAlign.Center, modifier = Modifier.width(30.dp))
            }
        }
    }
}

// 顶部输入框
@Composable
fun TodoInputBackground(modifier: Modifier = Modifier, elevate: Boolean, content: @Composable RowScope.() -> Unit) {
    val targetValue = if (elevate) 1.dp else 0.dp
    // 通过动画展示阴影部分。生成一个变化的0->1的数值
    val animation by animateDpAsState(targetValue = targetValue, TweenSpec(100))
    Surface(color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f), shape = RectangleShape, elevation = animation) {
        Row(modifier = modifier.animateContentSize(animationSpec = TweenSpec(200)), content = content)
    }
}

@Composable
fun TodoItemEntryInput(onAddClick: (TodoItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember {
        mutableStateOf(TodoIcon.Default)
    }
    val submit = {
        onAddClick(TodoItem(text, icon))
        setText("")
        setIcon(TodoIcon.Default)
    }
    TodoItemInput(text = text, onTextChanged = setText, icon = icon, onIconChanged = setIcon, onSubmit = submit) {
        TodoAddButton(
            "Add",
            onClick = submit,
            enable = text.isNotBlank(),
        )
    }
}

@Composable
fun TodoItemInput(
    text: String,
    onTextChanged: (String) -> Unit,
    icon: TodoIcon,
    onIconChanged: (TodoIcon) -> Unit,
    onSubmit: () -> Unit,
    buttonSlot: @Composable () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChanged = onTextChanged,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onDone = onSubmit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(CenterVertically)) {
                buttonSlot()
            }

        }
        IconRow(visible = text.isNotBlank(), icon = icon, onIconChange = onIconChanged)
    }
}

@Composable
fun IconRow(modifier: Modifier = Modifier, visible: Boolean, icon: TodoIcon, onIconChange: (TodoIcon) -> Unit) {
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
                    SelectableIconButton(
                        icon = it.vectorAsset,
                        onIconClick = { onIconChange(it) },
                        isSelected = it == icon
                    )
                }
            }
        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    onIconClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    TextButton(
        onClick = {
            onIconClick()
        },
        shape = CircleShape,
        modifier = modifier
    ) {
        Column {
            Icon(
                imageVector = icon, contentDescription = null,
                tint = tint
            )
            if (isSelected) {
                Box(
                    Modifier
                        .padding(top = 4.dp)
                        .width(icon.defaultWidth)
                        .height(2.dp)
                        .background(tint)
                )
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputText(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier, onDone: () -> Unit) {
    val controller = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onDone()
            controller?.hide()
        })
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