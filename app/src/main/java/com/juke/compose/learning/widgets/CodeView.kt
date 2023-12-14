package com.juke.compose.learning.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.juke.compose.learning.bean.toAnnotatedString
import com.juke.compose.learning.theme.CodeBlackBG
import com.juke.compose.learning.theme.CodeNormalWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun CodeView(codeText: AnnotatedString, modifier: Modifier = Modifier) {
    val _lineCount = MutableStateFlow(0)
    val paddingModifier = Modifier.padding(vertical = 10.dp)
    val style = TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
    )
    ConstraintLayout(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth(1.0f)
            .background(color = CodeBlackBG, shape = RoundedCornerShape(8.dp))
    ) {
        val (code, lines, divider) = createRefs()

        val lineCount by _lineCount.collectAsState()
        val sb = StringBuilder()
        for (i in 1..lineCount) {
            sb.append("$i\n")
        }
        Text(
            text = AnnotatedString(sb.dropLast(1).toString()),
            modifier = Modifier
                .then(paddingModifier)
                .constrainAs(lines) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(code.top)
                    bottom.linkTo(code.bottom)
                },
            textAlign = TextAlign.End,
            color = CodeNormalWhite,
            style = style
        )

        Box(
            modifier = Modifier
                .then(paddingModifier)
                .constrainAs(divider) {
                    top.linkTo(code.top)
                    bottom.linkTo(code.bottom)
                    start.linkTo(lines.end, margin = 10.dp)
                    height = Dimension.fillToConstraints
                }
                .background(color = CodeNormalWhite)
                .width(1.dp)
        )

        Text(
            text = codeText,
            modifier = Modifier
                .then(paddingModifier)
                .padding(horizontal = 10.dp)
                .constrainAs(code) {
                    start.linkTo(divider.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            onTextLayout = { result ->
                _lineCount.update { result.lineCount }
            },
            style = style
        )
    }
}