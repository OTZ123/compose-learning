package com.juke.compose.learning.i

import androidx.compose.material3.DrawerState
import com.juke.compose.learning.bean.ArticleEnum
import com.juke.compose.learning.m.MainViewModel

sealed class MainIntent() {

    class OnClick(private val selected : ArticleEnum, val index : Int) : MainIntent() {
        override suspend fun dealIntent(data: MainViewModel.Data) {
            data.selectedArticle.emit(selected)
        }
    }

    class ChangeDrawState(private val drawerState: DrawerState) : MainIntent() {
        override suspend fun dealIntent(data: MainViewModel.Data) {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    open suspend fun dealIntent(data : MainViewModel.Data) {

    }

}
