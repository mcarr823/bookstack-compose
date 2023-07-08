package dev.mcarr.android.ui.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mcarr.common.ui.screens.Setup

@Composable
@Preview
fun PreviewSetupView(){
    Surface {
        Setup(
            defaultEndpoint = "",
            defaultTokenId = "",
            defaultTokenSecret = "",
            connect = { _, _, _ ->}
        )
    }
}