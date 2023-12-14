package com.juke.compose.learning.v

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.juke.compose.learning.bean.ArticleView

class AnnotatedStringArticleView : ArticleView("AnnotatedString") {

    @Composable
    override fun CreateContent() {
        LazyColumn() {
            item {
                val builder = AnnotatedString.Builder("Hello World!")
                builder.addStyle(style = SpanStyle(color = Color.Blue), 0, 3)
                Text(text = builder.toAnnotatedString())
            }

            item {
                val builder = AnnotatedString.Builder("Hello World!")
                builder.addStyle(style = SpanStyle(fontSize = 24.sp), 0, 3)
                Text(text = builder.toAnnotatedString())
            }

            item {
                val builder = AnnotatedString.Builder("Hello World!")
                builder.addStyle(style = ParagraphStyle(textAlign = TextAlign.Center), 0, 3)
                Text(text = builder.toAnnotatedString())
            }

            item {
                val builder = AnnotatedString.Builder()
                builder.withStyle(SpanStyle(color = Color.Blue)) {
                    append("Hello ")
                }
                builder.withStyle(SpanStyle(color = Color.Red)) {
                    withStyle(SpanStyle(fontSize = 24.sp)) {
                        append("World!")
                    }
                }
                Text(text = builder.toAnnotatedString())
            }

            item {
                val builder = AnnotatedString.Builder("Hello World! ")
                builder.appendInlineContent(id = "inline", "inline content")
                Text(
                    text = builder.toAnnotatedString(),
                    inlineContent = mapOf(
                        "inline" to InlineTextContent(
                            Placeholder(
                                50.sp,
                                30.sp,
                                PlaceholderVerticalAlign.TextCenter
                            )
                        ) {
                            Text(text = it)
                        }
                    )
                )
            }
        }
    }
}