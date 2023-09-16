package dev.mcarr.common.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.ParentPageInterface
import dev.mcarr.common.network.ApiInterface
import dev.mcarr.common.ui.components.PageDetailsView

@Composable
fun PageDetailsScreen(
    db: AppDatabaseInterface,
    api: ApiInterface,
    pageId: Int,
    setName: (name: String) -> Unit,
    setRefresh: (refresh: suspend () -> Unit) -> Unit
) {

    var page by remember { mutableStateOf<ParentPageInterface?>(null) }
    val refresh: suspend () -> Unit = {

        setName("Loading...")
        page = null

        try{
            val tmpPage = api.getPage(pageId)
            db.setFullPage(tmpPage)

            page = tmpPage
            setName(tmpPage.name)
        }catch (e: Exception){
            e.printStackTrace()
            setName("Request Failed")
        }

    }

    setRefresh(refresh)
    setName("Loading...")

    PageDetailsView(page)

    LaunchedEffect(Unit) {
        val tmpPage = db.getFullPage(pageId)
        if (tmpPage != null) {
            page = tmpPage
            setName(tmpPage.name)
        } else {
            refresh()
        }
    }

}

