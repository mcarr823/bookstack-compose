package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.ui.components.ChapterDetailsView
import dev.mcarr.common.ui.components.PageListView

@Composable
fun ChapterDetailsScreen(
    chapter: ParentChapterInterface,
    pages: List<ParentPageInterface>,
    onTap: (page: ParentPageInterface) -> Unit
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

