package com.juke.compose.learning.bean

import androidx.compose.runtime.Composable
import com.juke.compose.learning.v.*

enum class ArticleEnum(private val articleData: ArticleView) {

    TEXT(TextArticleView()),
    TEXT_FIELD(TextFieldArticleView()),
    BUTTON(ButtonArticleView()),
    ICON(IconArticleView()),
    SWITCH(SwitchArticleView()),
    PROGRESS_INDICATOR(ProgressIndicatorArticleView()),
    SLIDE(SlideArticleView()),
    TEXT_STYLE(TextStyleArticleView()),
    ANNOTATED_STRING(AnnotatedStringArticleView()),
    MODIFY_ORDER(ModifyOrderArticleView()),
    MODIFY_SIZE(ModifySizeArticleView()),
    BOX(ViewGroupArticleView());

    fun value() = articleData

    @Composable
    fun CreateContent() {
        articleData.CreateContent()
    }
}

abstract class ArticleView(val title: String) {

    @Composable
    abstract fun CreateContent()
}