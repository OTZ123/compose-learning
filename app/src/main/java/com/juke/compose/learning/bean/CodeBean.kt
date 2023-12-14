package com.juke.compose.learning.bean

import android.graphics.Color as JavaColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.blankj.utilcode.util.GsonUtils

data class CodeBean(
    val text : String?,
    val color : String?
)

val ValCodeBean get() = CodeBean("val ", "CC7832")

fun String.toAnnotatedString() =
    GsonUtils.fromJson<List<CodeBean>>(
        this,
        GsonUtils.getListType(CodeBean::class.java)
    ).toAnnotatedString()

fun List<CodeBean>.toAnnotatedString() = let { beans ->
    val builder = AnnotatedString.Builder()
    for (bean in beans) {
        AnnotatedString.Builder().append(bean.text).apply {
            if (bean.color != "None" && !bean.color.isNullOrBlank()) {
                addStyle(
                    SpanStyle(color = Color(JavaColor.parseColor("#${bean.color}"))),
                    0,
                    bean.text?.length ?: 0
                )
            }
            builder.append(this.toAnnotatedString())
        }
    }
    builder
}.toAnnotatedString()