package dev.mcarr.common.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.BookInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.BookListView

@Composable
fun BookListScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    onTap: (book: BookInterface) -> Unit,
    setName: (name: String) -> Unit,
    setRefresh: (refresh: suspend () -> Unit) -> Unit
) {

    val books = remember { mutableStateListOf<BookInterface>() }
    val refresh: suspend () -> Unit = {
        setName("Loading...")
        books.clear()
        val newBooks = api.getBooks()
        db.setBooks(newBooks)
        books.addAll(newBooks.data)
        setName("Book List")
    }

    setRefresh(refresh)
    setName("Book List")

    BookListView(
        books = books,
        onTap = onTap
    )

    LaunchedEffect(Unit){
        db.getBooks().let(books::addAll)
        if (books.isEmpty()){
            refresh()
        }
    }

}
