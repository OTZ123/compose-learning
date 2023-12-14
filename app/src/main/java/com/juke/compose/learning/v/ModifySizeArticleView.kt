package com.juke.compose.learning.v

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.juke.compose.learning.bean.ArticleView

class ModifySizeArticleView : ArticleView("modify设置尺寸") {
    @Preview
    @Composable
    override fun CreateContent() {
        LazyColumn {
            item {
                Text(text = "默认大小根据子节点布局变化，类似wrap_content")
                Surface(color = Color.LightGray) {
                    Text(text = "Hello World!")
                }
                Surface(color = Color.LightGray) {
                    Text(text = "Hello World! Hello World! \nHello World!")
                }
            }

            item {
                Text(text = "使用width设置宽度")
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier.width(50.dp)
                ) {

                }
            }

            item {
                Text(text = "默认大小根据子节点布局变化，类似wrap_content")
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier.size(50.dp)
                ) {

                }
            }

            item {
                Text(text = "使用size定义宽度及高度")
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier.size(DpSize(40.dp, 20.dp))
                ) {

                }
            }

            item {
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier.size(40.dp, 20.dp)
                ) {

                }
            }

            item {
                Surface(
                    color = Color.LightGray,
                    modifier = Modifier.width(40.dp)
                ) {

                }
            }

            item {
                Text(text = "高阶用法IntrinsicSize（固定特性测量）")
                var showDialog by remember {
                    mutableStateOf(false)
                }
                if (showDialog) {
                    Dialog(onDismissRequest = { showDialog = false }) {
                        Row(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .height(IntrinsicSize.Min)
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 4.dp)
                                    .wrapContentWidth(Alignment.Start),
                                text = "start"
                            )

                            Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(10.dp)
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp)
                                    .wrapContentWidth(Alignment.End),
                                text = "end"
                            )
                        }
                    }
                }
                Button(onClick = {
                    showDialog = true
                }) {
                    Text(text = "click")
                }

            }
        }
    }
}