package dev.mcarr.common.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListContent(
    itemList: List<Pair<String, Screen>>,
    onItemClick: (Screen) -> Unit
) {
    val items = remember { itemList }

    LazyColumn {
        items(items = items) { item ->
            Text(
                text = item.first,
                modifier = Modifier
                    .clickable { onItemClick(item.second) }
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}