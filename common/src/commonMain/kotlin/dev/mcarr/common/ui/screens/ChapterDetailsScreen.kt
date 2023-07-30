package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.FullPageInterface
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.ChapterDetailsView
import dev.mcarr.common.ui.components.PageListView

@Composable
fun ChapterDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    chapterId: Int,
    onTap: (page: ParentPageInterface) -> Unit,
    setName: (name: String) -> Unit,
    setRefresh: (refresh: suspend () -> Unit) -> Unit
){

    var chapter by remember { mutableStateOf<ParentChapterInterface?>(null) }
    val pages = remember { mutableStateListOf<FullPageInterface>() }
    val refresh: suspend () -> Unit = {

        setName("Loading...")
        chapter = null
        pages.clear()

        val tmpChapter = api.getChapter(chapterId)
        db.setFullChapter(tmpChapter)

        tmpChapter.pages.forEach {
            val page = api.getPage(it.id)
            db.setFullPage(page)
            pages.add(page)
        }

        chapter = tmpChapter
        setName(tmpChapter.name)

    }

    setRefresh(refresh)
    setName("Loading...")

    Column {
        ChapterDetailsView(
            chapter = chapter
        )

        PageListView(
            pages = pages,
            onTap = onTap
        )
    }

    LaunchedEffect(Unit) {
        val tmpChapter = db.getFullChapter(chapterId)
        if (tmpChapter != null) {
            chapter = tmpChapter
            setName(tmpChapter.name)
        } else {
            refresh()
        }
    }

}

