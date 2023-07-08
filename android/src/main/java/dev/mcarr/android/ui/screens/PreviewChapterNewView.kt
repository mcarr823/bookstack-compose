package dev.mcarr.android.ui.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.common.ui.screens.ChapterNew

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