package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.interfaces.FullChapterInterface
import dev.mcarr.common.data.interfaces.PageInterface
import dev.mcarr.common.ui.components.ChapterDetailsView
import dev.mcarr.common.ui.components.PageListView

@Composable
fun ChapterDetailsScreen(
    chapter: FullChapterInterface,
    pages: List<PageInterface>,
    onTap: (page: PageInterface) -> Unit
){

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

