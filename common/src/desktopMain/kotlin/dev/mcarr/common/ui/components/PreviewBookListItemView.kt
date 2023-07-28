package dev.mcarr.common.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.fake.FakeAppDatabase
import dev.mcarr.common.ui.screens.BookListItemView
import kotlinx.coroutines.runBlocking

@Preview
@Composable
fun PreviewBookListItemView(){

    val api = FakeAppDatabase()
    val book = runBlocking { api.getBook(0) }

    MaterialTheme {
        Surface {
            BookListItemView(
                book = book,
                onTap = {}
            )
        }
    }
}