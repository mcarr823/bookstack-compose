package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
@Preview
fun PreviewSetupView(){
    Surface {
        Setup(
            defaultEndpoint = "",
            defaultTokenId = "",
            defaultTokenSecret = "",
            defaultDisableHttpsVerification = false,
            connect = {}
        )
    }
}