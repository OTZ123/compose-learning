package com.juke.compose.learning.m

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.juke.compose.learning.bean.ArticleEnum
import com.juke.compose.learning.i.MainIntent
import com.juke.compose.learning.v.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KProperty

class MainViewModel : ViewModel() {

    inner class Data {
        val articles = MutableStateFlow(ArticleEnum.values().toMutableList())
        val selectedArticle = MutableStateFlow(articles.value[0])
    }

    private val data = Data()

    @Composable
    fun remember() = MainActivity.MainState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        articlesState = data.articles.collectAsState(),
        selectedArticleState = data.selectedArticle.collectAsState()
    )

    suspend fun sendIntent(intent: MainIntent) {
        intent.dealIntent(data)
    }
}