package dev.mcarr.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.interfaces.PageInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PageListItemView(
    page: ParentPageInterface,
    onTap: (page: ParentPageInterface) -> Unit
) {

    val url = if (page is PageInterface) page.url else ""
    val updatedAt = page.updated_at.toLocalDateTime(TimeZone.UTC)
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
            onTap(page)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2,
                text = page.name
            )
            if (url.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h5,
                    text = url
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                //textAlign = TextAlign.End,
                text = lastUpdated
            )
        }

    }

}
