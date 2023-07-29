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
import dev.mcarr.common.data.interfaces.FullPageInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun PageDetailsView(
    page: ParentPageInterface?
) {

    val name: String
    val slug: String
    val html: String
    val lastUpdated: String
    if (page != null){
        name = page.name
        slug = page.slug
        html = if (page is FullPageInterface) page.html else ""
        val updatedAt = page.updated_at.toLocalDateTime(TimeZone.UTC)
        lastUpdated = "Last updated: %d:%02d, %d %s %04d".format(
            updatedAt.hour,
            updatedAt.minute,
            updatedAt.dayOfMonth,
            updatedAt.month.getDisplayName(TextStyle.SHORT, Locale.US),
            updatedAt.year
        )
    }else{
        name = "Loading..."
        slug = "Loading..."
        html = "Loading..."
        lastUpdated = "Loading..."
    }

    Column {
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
                    text = slug
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    //textAlign = TextAlign.End,
                    text = lastUpdated
                )
            }

        }

        Card(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            elevation = 5.dp
        ){
            Text(html)
        }

    }

}