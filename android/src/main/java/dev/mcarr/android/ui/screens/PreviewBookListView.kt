package dev.mcarr.android.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.common.data.fake.FakeAppDatabase
import dev.mcarr.common.ui.screens.BookListView
import kotlinx.coroutines.runBlocking

@Preview
@Composable
fun PreviewBookListView(){

    val db = FakeAppDatabase()
    val books = runBlocking { db.getBooks() }

    MaterialTheme {
        Surface {
            BookListView(
                books = books,
                onTap = {},
                onPull = {}
            )
        }
    }
}