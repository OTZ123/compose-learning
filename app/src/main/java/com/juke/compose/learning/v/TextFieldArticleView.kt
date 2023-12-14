package com.juke.compose.learning.v

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.juke.compose.learning.bean.ArticleView
import com.juke.compose.learning.theme.ParamGreen
import com.juke.compose.learning.theme.Pink80
import com.juke.compose.learning.theme.PurpleGrey40
import kotlinx.coroutines.launch

class TextFieldArticleView : ArticleView("输入框") {

    @Composable
    override fun CreateContent() {
        val snackbarHostState = remember {
            SnackbarHostState()
        }
        val scope = rememberCoroutineScope()

        fun showToast(text: String) {
            scope.launch {
                snackbarHostState.showSnackbar(text)
            }
        }

        LazyColumn() {

            item {
                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    leadingIcon = { Text(text = "前往") },
                    trailingIcon = { Text(text = "确定") },
                    label = { Text("Label") },
                    prefix = { Text("www.") },
                    suffix = { Text(".com") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    placeholder = { Text("google") },
                )
            }

            item {
                var text by remember { mutableStateOf("") }
                var isError by remember { mutableStateOf(false) }
                val charLimit = 2

                fun validate(text: String) {
                    isError = text.length > charLimit
                }

                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        validate(text)
                    },
                    singleLine = true,
                    label = { Text(if (isError) "Username*" else "Username") },
                    supportingText = {
                        if (isError) Text(
                            text = "Limit: ${text.length}/$charLimit"
                        )
                    },
                    isError = isError
                )
            }

            item {
                TextField(
                    value = "注意软键盘完成按钮",
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            showToast("onDone")
                            defaultKeyboardAction(imeAction = ImeAction.Done)
                        },
                        onGo = {
                            showToast("onGo")
                            defaultKeyboardAction(imeAction = ImeAction.Go)
                        },
                        onNext = {
                            showToast("onNext")
                            defaultKeyboardAction(imeAction = ImeAction.Next)
                        },
                        onPrevious = {
                            showToast("onPrevious")
                            defaultKeyboardAction(imeAction = ImeAction.Previous)
                        },
                        onSearch = {
                            showToast("onSearch")
                            defaultKeyboardAction(imeAction = ImeAction.Search)
                        },
                        onSend = {
                            showToast("onSend")
                            defaultKeyboardAction(imeAction = ImeAction.Send)
                        },
                    )
                )
            }

            item {
                var password by remember { mutableStateOf("") }
                var passwordHidden by remember { mutableStateOf(true) }
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    label = { Text("Enter password") },
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation('密') else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        Text(text = "切换", Modifier.clickable(true) {
                            passwordHidden = !passwordHidden
                        })
                    }
                )
            }

            item {
                var text by remember { mutableStateOf("scroll the text field to invoke dragged state if text field too short make it long long long long long long long") }
                val interactionSource = remember { MutableInteractionSource() }
                val focused by interactionSource.collectIsFocusedAsState()
                val pressed by interactionSource.collectIsPressedAsState()
                val dragged by interactionSource.collectIsDraggedAsState()
                val hovered by interactionSource.collectIsHoveredAsState()
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    singleLine = true,
                    label = { Text(text = if (focused) "focused true" else "focused false") },
                    interactionSource = interactionSource,
                    leadingIcon = { Text(text = if (pressed) "pressed true" else "pressed false") },
                    trailingIcon = { Text(text = if (dragged) "dragged true" else "dragged false") },
                    supportingText = { Text(text = if (hovered) "hovered true" else "hovered false because hover design for web") }
                )
            }

            item {
                var text by remember { mutableStateOf("Hello World!") }
                var isError by remember {
                    mutableStateOf(false)
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.colors(
                        errorTextColor = Color.Blue,
                        errorLabelColor = Color.Green,
                        focusedIndicatorColor = Color.Yellow,
                        errorContainerColor = Pink80,
                        selectionColors = TextSelectionColors(PurpleGrey40, ParamGreen),
                        cursorColor = Color.DarkGray,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    isError = isError,
                    label = { Text(text = "label") }
                )

                Text(text = "切换错误", modifier = Modifier.clickable(true) {
                    isError = !isError
                })
            }

            item {
                var text by remember { mutableStateOf("Hello World!") }
                var isError by remember {
                    mutableStateOf(false)
                }
                OutlinedTextField(value = text,
                    isError = isError,
                    onValueChange = { text = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = Color.Yellow
                    )
                )
                Text(text = "切换错误", modifier = Modifier.clickable(true) {
                    isError = !isError
                })
            }
        }
        SnackbarHost(hostState = snackbarHostState, Modifier)
    }
}