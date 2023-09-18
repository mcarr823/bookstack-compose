package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.classes.BookContentFormat
import dev.mcarr.common.data.classes.FullChapter
import dev.mcarr.common.data.classes.FullPage
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.ParentBookInterface
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.BookDetailsView
import dev.mcarr.common.ui.components.ChapterAndPageListView

@Composable
fun BookDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    bookId: Int,
    onTapChapter: (chapter: ParentChapterInterface) -> Unit,
    onTapPage: (chapter: ParentPageInterface) -> Unit,
    setName: (name: String) -> Unit,
    setRefresh: (refresh: suspend () -> Unit) -> Unit
) {

    var book by remember { mutableStateOf<ParentBookInterface?>(null) }
    val chapters = remember { mutableStateListOf<FullChapter>() }
    val pages = remember { mutableStateListOf<FullPage>() }

    val refresh: suspend () -> Unit = {

        setName("Loading...")
        book = null
        chapters.clear()
        pages.clear()

        try {
            val tmpBook = api.getBook(bookId)
            db.setBookFull(tmpBook)

            val total = tmpBook.contents.size
            tmpBook.contents.forEachIndexed { index, it ->
                setName("Downloaded $index of $total")
                if (it.type == BookContentFormat.CHAPTER.type) {
                    val chapter = api.getChapter(it.id)
                    db.setFullChapter(chapter)
                    chapters.add(chapter)
                } else if (it.type == BookContentFormat.PAGE.type) {
                    val page = api.getPage(it.id)
                    db.setFullPage(page)
                    pages.add(page)
                }
            }

            book = tmpBook
            setName(tmpBook.name)
        }catch (e: Exception){
            e.printStackTrace()
            setName("Request Failed")
        }

    }

    setRefresh(refresh)
    setName("Loading...")

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

    LaunchedEffect(Unit){
        val tmpBook = db.getBookFull(bookId)
        if (tmpBook != null){
            setName(tmpBook.name)
            db.getFullChaptersByBookId(bookId).let(chapters::addAll)
            db.getFullPagesByBookId(bookId).let(pages::addAll)
            book = tmpBook
        }else{
            refresh()
        }
    }

}