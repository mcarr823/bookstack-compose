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
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.FullPageInterface
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.ChapterDetailsView
import dev.mcarr.common.ui.components.PageListView
import kotlinx.coroutines.launch

@Composable
fun ChapterDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    chapterId: Int,
    onTap: (page: ParentPageInterface) -> Unit,
    goBack: () -> Unit
){

    val coroutineScope = rememberCoroutineScope()
    var chapter by remember { mutableStateOf<ParentChapterInterface?>(null) }
    var name by remember { mutableStateOf("Loading...") }
    val pages = remember { mutableStateListOf<FullPageInterface>() }

    val refresh: suspend () -> Unit = {

        name = "Loading..."
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
        name = tmpChapter.name

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
            ) {
                Icon(Icons.Filled.Refresh, "")
            }
        }
    ) {
        Column {
            ChapterDetailsView(
                chapter = chapter
            )

            PageListView(
                pages = pages,
                onTap = onTap
            )
        }
    }

    LaunchedEffect(Unit) {
        val tmpChapter = db.getFullChapter(chapterId)
        if (tmpChapter != null) {
            chapter = tmpChapter
            name = tmpChapter.name
        } else {
            refresh()
        }
    }

}

