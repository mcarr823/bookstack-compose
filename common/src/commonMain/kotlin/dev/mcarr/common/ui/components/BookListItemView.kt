package dev.mcarr.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.classes.Book
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookListItemView(
    book: Book,
    onTap: (book: Book) -> Unit
){

    val updatedAt = book.updated_at.toLocalDateTime(TimeZone.UTC)
    val lastUpdated = "Last updated: %d:%02d, %d %s %04d".format(
        updatedAt.hour,
        updatedAt.minute,
        updatedAt.dayOfMonth,
        updatedAt.month.getDisplayName(TextStyle.SHORT, Locale.US),
        updatedAt.year
    )

    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = 5.dp,
        onClick = {
            onTap(book)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2,
                text = book.name
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h5,
                text = book.description
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                //textAlign = TextAlign.End,
                text = lastUpdated
            )
        }

    }

}

