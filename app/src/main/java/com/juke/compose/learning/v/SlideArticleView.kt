package com.juke.compose.learning.v

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.juke.compose.learning.bean.ArticleView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class SlideArticleView : ArticleView("滚动条组件") {
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
        LazyColumn {
            item {
                var sliderPosition by remember { mutableStateOf(0f) }
                Column {
                    Text(text = sliderPosition.toString())
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it })
                }
            }

            item {
                var sliderPosition by remember { mutableStateOf(0f) }
                Text(text = sliderPosition.toString())
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                    },
                    valueRange = 0f..100f,
                    onValueChangeFinished = {
                        showToast("onValueChangeFinished")
                    },
                    steps = 9,
                    colors = SliderDefaults.colors(
                        activeTrackColor = Color.Yellow,
                        thumbColor = Color.Blue,
                        inactiveTrackColor = Color.Green
                    ),
                    thumb = {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = Color.Red
                        )
                    },
                )
            }

            item {
                val valueRange = 0f..100f
                var rangeSliderState by remember {
                    mutableStateOf(valueRange)
                }
                Text(text = rangeSliderState.toString())
                RangeSlider(
                    value = rangeSliderState,
                    onValueChange = {
                        rangeSliderState = it
                    },
                    valueRange = valueRange,
                    steps = 24,
                    startThumb = {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    },
                    endThumb = {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
                    }
                )
            }
        }
        SnackbarHost(hostState = snackbarHostState, Modifier)
    }
}