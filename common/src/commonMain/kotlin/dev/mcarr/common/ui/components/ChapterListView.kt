package dev.mcarr.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.interfaces.ParentChapterInterface

@Composable
fun ChapterListView(
    chapters: List<ParentChapterInterface>,
    onTap: (chapter: ParentChapterInterface) -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
    ){
        items(chapters){ chapter ->
            ChapterListItemView(
                chapter = chapter,
                onTap = onTap
            )
        }
        //TODO: pull to refresh
    }

}
