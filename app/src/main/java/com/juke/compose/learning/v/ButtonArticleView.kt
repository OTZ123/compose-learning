package com.juke.compose.learning.v

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.juke.compose.learning.bean.ArticleView
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
class ButtonArticleView : ArticleView("按钮组件") {
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
                val interactionSource = remember { MutableInteractionSource() }
                val press by interactionSource.collectIsPressedAsState()
                Button(
                    onClick = {
                        showToast("onclick")
                    },
                    interactionSource = interactionSource
                ) {
                    Text(text = if (press) "press" else "normal")
                }
            }
            item {
                Button(
                    onClick = { showToast("onclick") },
                    enabled = false,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.Yellow),
                    elevation = ButtonDefaults.buttonElevation(disabledElevation = 15.dp),
                    border = BorderStroke(2.dp, Color.Blue),
                    contentPadding = PaddingValues(20.dp),
                    modifier = Modifier.padding(10.dp),
                ) {
                    Text(text = "button")
                }
            }

            item {
                OutlinedButton(
                    onClick = { showToast("OutlinedButton onclick") }
                ) {
                    Text(text = "OutlinedButton")
                }
            }

            item {
                IconButton(onClick = {
                    showToast("IconButton onclick")
                }) {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
            }

            item {
                var checked by remember { mutableStateOf(false) }
                IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                    if (checked) {
                        Icon(Icons.Filled.Lock, contentDescription = "Localized description")
                    } else {
                        Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                    }
                }
            }

            item {
                val radioOptions = listOf("Calls", "Missed", "Friends")
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            }
        }
        SnackbarHost(hostState = snackbarHostState, Modifier)
    }
}