package dev.mcarr.common.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.BookInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.BookListView
import kotlinx.coroutines.launch

@Composable
fun BookListScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    onTap: (book: BookInterface) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val books = remember { mutableStateListOf<BookInterface>() }
    val refresh: suspend () -> Unit = {
        val newBooks = api.getBooks()
        db.setBooks(newBooks)
        books.clear()
        books.addAll(newBooks.data)
    }

    //TODO: if refreshing, indicate somehow
    BookListView(
        books = books,
        onTap = onTap,
        onPull = {
            coroutineScope.launch {
                refresh()
            }
        }
    )

    LaunchedEffect(Unit){
        db.getBooks().let(books::addAll)
        if (books.isEmpty()){
            refresh()
        }
    }

}
