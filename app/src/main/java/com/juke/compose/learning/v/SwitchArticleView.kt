package com.juke.compose.learning.v

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.juke.compose.learning.bean.ArticleView

class SwitchArticleView : ArticleView("开关组件") {
    @Composable
    override fun CreateContent() {
        LazyColumn {
            item {
                var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it })
            }

            item {
                var checked by remember { mutableStateOf(true) }
                val icon: (@Composable () -> Unit)? = if (checked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }

                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    thumbContent = icon,
                    colors =  SwitchDefaults.colors(
                        checkedTrackColor = Color.Red,
                        checkedThumbColor = Color.Yellow,
                        checkedIconColor = Color.Blue
                    )
                )
            }
        }
    }
}