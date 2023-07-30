package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.BookInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.BookDetailsView
import dev.mcarr.common.ui.components.BookListView
import dev.mcarr.common.ui.components.ChapterAndPageListView
import kotlinx.coroutines.launch

@Composable
fun BookListScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    onTap: (book: BookInterface) -> Unit,
    goBack: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val books = remember { mutableStateListOf<BookInterface>() }
    var name by remember { mutableStateOf("Book List") }
    val refresh: suspend () -> Unit = {
        name = "Loading..."
        val newBooks = api.getBooks()
        db.setBooks(newBooks)
        books.clear()
        books.addAll(newBooks.data)
        name = "Book List"
    }

    //TODO: if refreshing, indicate somehow
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name) },
                navigationIcon = {
                    IconButton(
                        onClick = { goBack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        refresh()
                    }
                }
            ){
                Icon(Icons.Filled.Refresh, "")
            }
        }
    ) {
        BookListView(
            books = books,
            onTap = onTap,
            onPull = {
                coroutineScope.launch {
                    refresh()
                }
            }
        )
    }

    LaunchedEffect(Unit){
        db.getBooks().let(books::addAll)
        if (books.isEmpty()){
            refresh()
        }
    }

}
