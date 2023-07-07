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
                    created_at = Clock.System.now(),
                    created_by = 0,
                    updated_at = Clock.System.now(),
                    updated_by = 0,
                    owned_by = 0
                ),
                onTap = {}
            )
        }
    }
}