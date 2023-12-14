package com.juke.compose.learning.v

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.juke.compose.learning.bean.ArticleView
import java.util.Locale as JavaLocal

class TextStyleArticleView : ArticleView("TextStyle") {

    @Composable
    override fun CreateContent() {
        LazyColumn() {
            item {
                Text(text = "TextStyle(fontFeatureSettings = null)")

                val textStyle = TextStyle(fontFeatureSettings = "smcp")
                Text(text = "TextStyle(fontFeatureSettings = \"smcp\")", style = textStyle)

            }
            item {
                ConstraintLayout(modifier = Modifier.fillMaxWidth(1.0f)) {
                    val (tv, ref) = createRefs()
                    val textStyle = TextStyle(baselineShift = BaselineShift.Superscript)

                    Text(
                        text = "BaselineShift.Superscript",
                        style = textStyle,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.constrainAs(tv) {
                            start.linkTo(parent.start)
                        },
                        fontSize = TextUnit(20f, TextUnitType.Sp)
                    )

                    Text(
                        text = "ref",
                        modifier = Modifier.constrainAs(ref) {
                            start.linkTo(tv.end)
                            baseline.linkTo(tv.baseline)
                        },
                    )
                }
            }

            item {
                Text(text = "scaleX 代表缩放尺度，skewX代表倾斜角度")
                val textStyle =
                    TextStyle(textGeometricTransform = TextGeometricTransform(0.5f, -0.5f))
                Text(
                    text = "TextGeometricTransform(0.5f, -0.5f)",
                    style = textStyle
                )
            }

            item {
                Text(text = "本地化显示,对应Paint中Paint.setTextLocale(Locale locale)方法")
                Text(
                    text = "雨骨底条今直沿微写"
                )
                Text(
                    text = "雨骨底条今直沿微写",
                    style = TextStyle(localeList = LocaleList(Locale(JavaLocal.TAIWAN.toLanguageTag())))
                )
                Text(
                    text = "雨骨底条今直沿微写",
                    style = TextStyle(localeList = LocaleList(Locale(JavaLocal.JAPAN.toLanguageTag())))
                )
            }

            item {
                Text(text = "阴影颜色，阴影偏移，阴影高斯模糊不影响其他布局")

                Text(
                    text = "Shadow(Color.Red, offset = Offset(.0f, 8.0f), blurRadius = 8.0f)",
                    style = TextStyle(
                        shadow = Shadow(
                            Color.Red,
                            offset = Offset(.0f, 8.0f),
                            blurRadius = 8.0f
                        )
                    )
                )
            }

            item {
                Text(
                    text = "textIndent首行缩进及剩下换行后缩进，/n代表开启新段落，适用于首行缩进\ntextIndent = TextIndent(20.sp, 5.sp)",
                    style = TextStyle(textIndent = TextIndent(20.sp, 5.sp))
                )
            }

            item {
                val noneStyle =
                    TextStyle(
                        //这里增加行距，让lineHeightStyle的属性效果更明显
                        lineHeight = 3.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        ),
                        //加个背景,可以看的更清楚行距的区别
                        background = Color.Gray
                    )
                val bothStyle =
                    TextStyle(
                        lineHeight = 3.em,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        ),
                        background = Color.Gray
                    )
                val lastLineBottomStyle =
                    TextStyle(
                        lineHeight = 3.em,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.LastLineBottom
                        ),
                        background = Color.Gray
                    )
                val firstLineTopStyle =
                    TextStyle(
                        lineHeight = 3.em,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.FirstLineTop
                        ),
                        background = Color.Gray
                    )
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = "文字在行距中间\nLineHeightStyle.Alignment.Center\nLineHeightStyle.Trim.None",
                            style = noneStyle
                        )
                        Text(
                            text = "第一行在行距上面，最后一行在行距下面，中间行在中间\nLineHeightStyle.Alignment.Center\nLineHeightStyle.Trim.Both",
                            style = bothStyle,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = "文字在行距下面\nLineHeightStyle.Alignment.Center\nLineHeightStyle.Trim.LastLineBottom",
                            style = lastLineBottomStyle,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Text(
                            text = "第一行在行距上面，其他行在中间\nLineHeightStyle.Alignment.Center\nLineHeightStyle.Trim.FirstLineTop",
                            style = firstLineTopStyle,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }
            }
            item {
                val simple = TextStyle(
                    /**
                    LineBreak.Simple 快速的断行算法。非常适合经常更新的文本，如文本编辑器，因为文本将回流最小。
                     */
                    lineBreak = LineBreak.Simple,
                )

                val heading = TextStyle(
                    /**
                    LineBreak.Heading 平衡行长度、连字符和基于短语的断行。适用于短文本，如标题或狭窄的报纸专栏。
                     */
                    lineBreak = LineBreak.Heading,
                )

                val paragraph = TextStyle(
                    /**
                    LineBreak.Paragraph 更慢、更高质量的断行，以提高可读性。适用于大量的文本。
                     */
                    lineBreak = LineBreak.Paragraph,
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = "快速的断行算法。非常适合经常更新的文本，如文本编辑器，因为文本将回流最小。",
                            style = simple,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .size(100.dp)
                        )
                        Text(
                            text = "平衡行长度、连字符和基于短语的断行。适用于短文本，如标题或狭窄的报纸专栏。",
                            style = heading,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .size(100.dp)
                        )
                        Text(
                            text = "更慢、更高质量的断行，以提高可读性。适用于大量的文本。",
                            style = paragraph,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .size(100.dp)
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Experimental",
                    style = TextStyle(hyphens = Hyphens.Auto),
                    modifier = Modifier.width(50.dp)
                )
                Text(
                    text = "Experimental",
                    modifier = Modifier.width(50.dp)
                )
            }

            item {
                Text(text = "Hello World", color = Color.Blue, style = TextStyle(color = Color.Red))
            }

            item {
                val textStyle =
                    TextStyle(color = Color.Blue, fontSize = 24.sp) +
                            TextStyle(color = Color.Red)
                Text(text = "Hello World", style = textStyle)
            }
        }
    }
}