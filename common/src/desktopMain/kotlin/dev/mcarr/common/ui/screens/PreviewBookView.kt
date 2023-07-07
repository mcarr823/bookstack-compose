package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.Book
import kotlinx.datetime.Clock

@Preview
@Composable
fun PreviewBookView(){
    MaterialTheme {
        Surface {
            BookView(
                book = Book(
                    id = 0,
                    name = "Book Title",
                    slug = "",
                    description = "This is an example book description",
                    createdAt = Clock.System.now(),
                    createdBy = 0,
                    updatedAt = Clock.System.now(),
                    updatedBy = 0,
                    ownedBy = 0
                ),
                onTap = {}
            )
        }
    }
}