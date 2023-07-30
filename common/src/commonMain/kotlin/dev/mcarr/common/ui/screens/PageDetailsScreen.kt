package dev.mcarr.common.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.PageDetailsView
import kotlinx.coroutines.launch

@Composable
fun PageDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    pageId: Int,
    goBack: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    var page by remember { mutableStateOf<ParentPageInterface?>(null) }
    var name by remember { mutableStateOf("Loading...") }

    val refresh: suspend () -> Unit = {

        name = "Loading..."
        page = null

        val tmpPage = api.getPage(pageId)
        db.setFullPage(tmpPage)

        page = tmpPage
        name = tmpPage.name

    }

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
            ) {
                Icon(Icons.Filled.Refresh, "")
            }
        }
    ) {
        PageDetailsView(page)
    }

    LaunchedEffect(Unit) {
        val tmpPage = db.getFullPage(pageId)
        if (tmpPage != null) {
            page = tmpPage
            name = tmpPage.name
        } else {
            refresh()
        }
    }

}

