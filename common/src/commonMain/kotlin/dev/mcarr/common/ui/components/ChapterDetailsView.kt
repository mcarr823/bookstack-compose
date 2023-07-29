package dev.mcarr.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.interfaces.ParentChapterInterface
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ChapterDetailsView(
    chapter: ParentChapterInterface?
) {

    val name: String
    val description: String
    val lastUpdated: String
    if (chapter != null){
        name = chapter.name
        description = chapter.description
        val updatedAt = chapter.updated_at.toLocalDateTime(TimeZone.UTC)
        lastUpdated = "Last updated: %d:%02d, %d %s %04d".format(
            updatedAt.hour,
            updatedAt.minute,
            updatedAt.dayOfMonth,
            updatedAt.month.getDisplayName(TextStyle.SHORT, Locale.US),
            updatedAt.year
        )
    }else{
        name = "Loading..."
        description = "Loading..."
        lastUpdated = "Loading..."
    }

    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2,
                text = name
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h5,
                text = description
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