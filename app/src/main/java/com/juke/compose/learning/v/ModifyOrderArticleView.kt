package com.juke.compose.learning.v

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juke.compose.learning.bean.ArticleView

class ModifyOrderArticleView : ArticleView("modify执行顺序") {
    @Composable
    override fun CreateContent() {
        LazyColumn{
            item {
                val padding = 16.dp
                Column(
                    Modifier
                        .clickable(onClick = { })
                        .padding(padding)
                        .fillMaxWidth()
                ) {
                   Text(text = "点击区域查看不同")
                }

                Column(
                    Modifier
                        .padding(padding)
                        .clickable(onClick = {})
                        .fillMaxWidth()
                ) {
                    Text(text = "点击区域查看不同")
                }
            }
        }
    }

}