package com.juke.compose.learning.v

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.juke.compose.learning.bean.ArticleView
import com.juke.compose.learning.bean.toAnnotatedString
import com.juke.compose.learning.widgets.CodeView

class TextArticleView : ArticleView("文本组件") {

    private val code1 =
        """[{"text": "Text", "color": "A9B7C6"}, {"text": "(", "color": "A9B7C6"}, {"text": "text = ", "color": "467CDA"}, {"text": "\"Hello World!\"", "color": "6A8759"}, {"text": ", ", "color": "CC7832"}, {"text": "color = ", "color": "467CDA"}, {"text": "Color.", "color": "A9B7C6"}, {"text": "Blue", "color": "9876AA"}, {"text": ")", "color": "A9B7C6"}]"""
            .toAnnotatedString()

    @Composable
    override fun CreateContent() {
        View()
    }

    @Composable
    fun View() {
        Column() {
            CodeView(code1)
            Text(text = "Hello World!", color = Color.Blue)
            Text(text = "Hello World!", fontSize = TextUnit(20f, TextUnitType.Sp))
            Text(text = "Hello World!", fontStyle = FontStyle.Italic)
            Text(text = "Hello World!", letterSpacing = TextUnit(20f, TextUnitType.Sp))
            Text(text = "Hello World!", textDecoration = TextDecoration.LineThrough)
            Text(
                text = "Hello World!", textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1.0f)
            )
            Text(text = "First Line \nSecondLine", lineHeight = TextUnit(30f, TextUnitType.Sp))
            Text(text = "First Line \nSecondLine", maxLines = 1)
            Text(
                text = "Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! ",
                softWrap = false
            )

            Text(text = "Hello World!", minLines = 2)

            Text(
                text = "Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! Hello World! ",
                overflow = TextOverflow.Visible,
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2),
                softWrap = false
            )
        }
    }

}