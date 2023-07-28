package dev.mcarr.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mcarr.common.data.interfaces.PageInterface
import dev.mcarr.common.ui.components.PageListItemView

@Composable
fun PageListView(
    pages: List<PageInterface>,
    onTap: (page: PageInterface) -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
    ){
        items(pages){ page ->
            PageListItemView(
                page = page,
                onTap = onTap
            )
        }
        //TODO: pull to refresh
    }

}