package com.juke.compose.learning.v

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.juke.compose.learning.bean.ArticleView

class ProgressIndicatorArticleView : ArticleView("进度条组件") {
    @Composable
    override fun CreateContent() {
        LazyColumn {

            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LinearProgressIndicator()
                }
            }

            item {
                var progress by remember { mutableStateOf(0.1f) }
                val animatedProgress by animateFloatAsState(
                    targetValue = progress,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LinearProgressIndicator(
                        progress = animatedProgress,
                        color = Color.Red,
                        trackColor = Color.Yellow,
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(Modifier.requiredHeight(30.dp))
                    OutlinedButton(
                        onClick = {
                            if (progress < 1f) progress += 0.1f
                        }
                    ) {
                        Text("Increase")
                    }
                }
            }

            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }

            item {
                var progress by remember { mutableStateOf(0.1f) }
                val animatedProgress by animateFloatAsState(
                    targetValue = progress,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        progress = animatedProgress,
                        color = Color.Red,
                        strokeWidth = 5.dp,
                        trackColor = Color.Yellow,
                        strokeCap = StrokeCap.Square
                    )
                    Spacer(Modifier.requiredHeight(30.dp))
                    OutlinedButton(
                        onClick = {
                            if (progress < 1f) progress += 0.1f
                        }
                    ) {
                        Text("Increase")
                    }
                }
            }
        }
    }
}