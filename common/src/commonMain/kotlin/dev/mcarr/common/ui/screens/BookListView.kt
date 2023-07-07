package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.Book
import dev.mcarr.common.data.Books

@Composable
fun BookListView(
    books: Books,
    onTap: (book: Book) -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
    ){
        items(books.total){ index ->
            BookView(
                book = books.data[index],
                onTap = onTap
            )
        }
    }

}