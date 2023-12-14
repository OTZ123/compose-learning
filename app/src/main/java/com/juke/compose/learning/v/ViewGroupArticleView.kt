package com.juke.compose.learning.v

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juke.compose.learning.bean.ArticleView

class ViewGroupArticleView : ArticleView("常用布局") {
    @Composable
    override fun CreateContent() {
        LazyColumn {
            item {
                Text(text = "基础布局，类似FrameLayout")
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.LightGray)
                ) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Yellow)
                    )
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Red)
                            .align(Alignment.Center)
                    )
                }
            }

            item {
                Text(text = "可以content中获取BoxWithConstraints的minWidth，maxWidth，minHeight，maxHeight")
                BoxWithConstraints(Modifier.height(150.dp)) {
                    val rectangleHeight = 100.dp
                    if (maxHeight < rectangleHeight * 2) {
                        Box(
                            Modifier
                                .size(50.dp, rectangleHeight)
                                .background(Color.Blue)
                        )
                    } else {
                        Column {
                            Box(
                                Modifier
                                    .size(50.dp, rectangleHeight)
                                    .background(Color.Blue)
                            )
                            Box(
                                Modifier
                                    .size(50.dp, rectangleHeight)
                                    .background(Color.Gray)
                            )
                        }
                    }
                }
            }
        }
    }
}