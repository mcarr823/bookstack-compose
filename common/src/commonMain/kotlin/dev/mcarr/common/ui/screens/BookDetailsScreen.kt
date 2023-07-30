package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.classes.BookContentFormat
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.FullChapterInterface
import dev.mcarr.common.data.interfaces.FullPageInterface
import dev.mcarr.common.data.interfaces.ParentBookInterface
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.BookDetailsView
import dev.mcarr.common.ui.components.ChapterAndPageListView
import dev.mcarr.common.ui.components.ChapterListView
import dev.mcarr.common.ui.components.PageListView
import kotlinx.coroutines.launch

@Composable
fun BookDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    bookId: Int,
    onTapChapter: (chapter: ParentChapterInterface) -> Unit,
    onTapPage: (chapter: ParentPageInterface) -> Unit,
    goBack: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("Loading...") }
    var book by remember { mutableStateOf<ParentBookInterface?>(null) }
    val chapters = remember { mutableStateListOf<FullChapterInterface>() }
    val pages = remember { mutableStateListOf<FullPageInterface>() }

    val refresh: suspend () -> Unit = {

        book = null
        chapters.clear()
        pages.clear()

        val tmpBook = api.getBook(bookId)
        db.setBookFull(tmpBook)
        tmpBook.contents.forEach {
            if (it.type == BookContentFormat.CHAPTER.type){
                val chapter = api.getChapter(it.id)
                db.setFullChapter(chapter)
                chapters.add(chapter)
            }else if (it.type == BookContentFormat.PAGE.type){
                val page = api.getPage(it.id)
                db.setFullPage(page)
                pages.add(page)
            }
        }
        name = tmpBook.name
        book = tmpBook

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name) },
                navigationIcon = {
                    IconButton(
                        onClick = { goBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        refresh()
                    }
                }
            ){
                Icon(Icons.Filled.Refresh, "")
            }
        }
    ) {
        Column {
            BookDetailsView(
                book = book
            )

        ChapterAndPageListView(
            chapters = chapters,
            pages = pages,
            onTapChapter = onTapChapter,
            onTapPage = onTapPage
        )


            //TODO: pull to refresh
        }
    }

    LaunchedEffect(Unit){
        val tmpBook = db.getBookFull(bookId)
        if (tmpBook != null){
            name = tmpBook.name
            db.getFullChaptersByBookId(bookId).let(chapters::addAll)
            db.getFullPagesByBookId(bookId).let(pages::addAll)
            book = tmpBook
        }else{
            refresh()
        }
    }

}