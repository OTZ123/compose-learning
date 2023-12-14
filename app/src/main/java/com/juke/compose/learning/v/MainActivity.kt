package com.juke.compose.learning.v

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.juke.compose.learning.bean.ArticleEnum
import com.juke.compose.learning.i.MainIntent
import com.juke.compose.learning.m.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SideDrawer(viewModel.remember())
        }
    }

    @Composable
    fun SideDrawer(viewState: MainState) {
        ModalNavigationDrawer(
            drawerState = viewState.drawerState,
            drawerContent = { DrawerSheet(viewState) }
        ) {
            viewState.selectedArticleState.value.CreateContent()
        }
    }

    @Composable
    fun DrawerSheet(viewState: MainState) {
        val scope = rememberCoroutineScope()
        ModalDrawerSheet(
            modifier = Modifier.size(
                LocalConfiguration.current.screenWidthDp.dp * 2 / 5,
                LocalConfiguration.current.screenHeightDp.dp
            )
        ) {
            Text("目录", modifier = Modifier.padding(16.dp))
            Divider()
            LazyColumn{
                viewState.articlesState.value.forEachIndexed { index, it ->
                    item {
                        NavigationDrawerItem(
                            label = { Text(text = it.value().title) },
                            selected = viewState.selectedArticleState.value == it,
                            onClick = {
                                scope.launch {
                                    viewModel.sendIntent(MainIntent.ChangeDrawState(viewState.drawerState))
                                    viewModel.sendIntent(MainIntent.OnClick(it, index))
                                }
                            },
                            shape = RoundedCornerShape(CornerSize(0.dp))
                        )
                    }
                }
            }

        }
    }

    data class MainState(
        val drawerState: DrawerState,
        val selectedArticleState: State<ArticleEnum>,
        val articlesState: State<List<ArticleEnum>>
    )
}