package dev.mcarr.android.ui.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.common.ui.screens.BookNew

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