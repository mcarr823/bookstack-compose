package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
@Preview
fun PreviewChapterNewView(){
    Surface {
        ChapterNew(
            bookId = -1,
            chapterId = null,
            defaultName = "",
            defaultDescription = "",
            submit = {_, _, _, _ ->}
        )
    }
}