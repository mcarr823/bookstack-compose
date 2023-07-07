package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.Book
import dev.mcarr.common.data.Books
import kotlinx.datetime.Clock

@Preview
@Composable
fun PreviewBookListView(){

    val books = ArrayList<Book>()
    for (i in 1 until 20){
        val book = Book(
            id = i,
            name = "Book $i",
            slug = "",
            description = "This is an example book description #$i",
            createdAt = Clock.System.now(),
            createdBy = 0,
            updatedAt = Clock.System.now(),
            updatedBy = 0,
            ownedBy = 0
        )
        books.add(book)
    }

    MaterialTheme {
        Surface {
            BookListView(
                books = Books(
                    data = books,
                    total = books.size
                ),
                onTap = {}
            )
        }
    }
}