package dev.mcarr.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import dev.mcarr.common.data.AppDatabase

@Preview
@Composable
fun AppPreview() {
    val db = AppDatabase()
    App(db)
}