package dev.mcarr.common

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.network.API
import dev.mcarr.common.ui.screens.BookListScreen
import dev.mcarr.common.ui.screens.BookDetailsScreen
import dev.mcarr.common.ui.navigation.ListContent
import dev.mcarr.common.ui.navigation.MyChildStack
import dev.mcarr.common.ui.navigation.Screen
import dev.mcarr.common.ui.screens.ChapterDetailsScreen
import dev.mcarr.common.ui.screens.PageDetailsScreen
import dev.mcarr.common.ui.screens.Setup
import kotlinx.coroutines.launch

@Composable
fun App(
    db: AppDatabaseInterface
) {

    val navigation = remember { StackNavigation<Screen>() }
    val menu = listOf(
        "Setup" to Screen.Setup,
        "Books" to Screen.BookList
    )

    var api = API(
        apiUrl = "https://my.website.com",
        tokenId = "tokenId",
        tokenSecret = "tokenSecret",
        disableHttpsVerification = true,
        testing = true
    )
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("Bookstack Compose") }
    var hasRefresh by remember { mutableStateOf(false) }
    var hasBackButton by remember { mutableStateOf(false) }

    val goBack = {
        navigation.pop()
    }
    var refresh: suspend () -> Unit by remember { mutableStateOf({}) }
    val setRefresh: (refresh: suspend () -> Unit) -> Unit = {
        refresh = it
    }
    val setName: (name: String) -> Unit = {
        name = it
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name) },
                navigationIcon = {
                    if (hasBackButton) {
                        IconButton(
                            onClick = { goBack() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (hasRefresh) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            refresh()
                        }
                    }
                ) {
                    Icon(Icons.Filled.Refresh, "")
                }
            }
        }
    ) {
        MyChildStack(
            source = navigation,
            initialStack = { listOf(Screen.List) },
            handleBackButton = true,
            animation = stackAnimation(fade() + scale()),
        ) { screen ->
            when (screen) {

                is Screen.List -> {
                    name = "Main Menu"
                    hasRefresh = false
                    hasBackButton = false
                    ListContent(
                        itemList = menu,
                        onItemClick = { navigation.push(it) }
                    )
                }

                is Screen.Setup -> {
                    name = "Setup"
                    hasRefresh = false
                    hasBackButton = true
                    Setup(
                        defaultEndpoint = api.apiUrl,
                        defaultTokenId = api.tokenId,
                        defaultTokenSecret = api.tokenSecret,
                        defaultDisableHttpsVerification = api.disableHttpsVerification,
                    ) { newApi ->
                        api = newApi
                    }
                }

                is Screen.BookList -> {
                    hasRefresh = true
                    hasBackButton = true
                    BookListScreen(
                        db = db,
                        api = api,
                        onTap = { book -> navigation.push(Screen.Book(book.id)) },
                        setName = setName,
                        setRefresh = setRefresh
                    )
                }

                is Screen.Book -> {
                    hasRefresh = true
                    hasBackButton = true
                    BookDetailsScreen(
                        db = db,
                        api = api,
                        bookId = screen.bookId,
                        onTapChapter = { chapter -> navigation.push(Screen.Chapter(chapter.id)) },
                        onTapPage = { page -> navigation.push(Screen.Page(page.id)) },
                        setName = setName,
                        setRefresh = setRefresh
                    )
                }

                is Screen.Chapter -> {
                    hasRefresh = true
                    hasBackButton = true
                    ChapterDetailsScreen(
                        db = db,
                        api = api,
                        chapterId = screen.chapterId,
                        onTap = { page -> navigation.push(Screen.Page(page.id)) },
                        setName = setName,
                        setRefresh = setRefresh
                    )
                }

                is Screen.Page -> {
                    hasRefresh = true
                    hasBackButton = true
                    PageDetailsScreen(
                        db = db,
                        api = api,
                        pageId = screen.pageId,
                        setName = setName,
                        setRefresh = setRefresh
                    )
                }

                else -> {
                    //unknown screen
                }
            }
        }
    }

}