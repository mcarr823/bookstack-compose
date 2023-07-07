package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
@Preview
fun PreviewBookNewView(){
    Surface {
        BookNew(
            bookId = null,
            defaultName = "",
            defaultDescription = "",
            submit = {_, _, _ ->}
        )
    }
}