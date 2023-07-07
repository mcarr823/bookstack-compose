package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Setup(
    defaultEndpoint: String,
    defaultTokenId: String,
    defaultTokenSecret: String,
    connect: (endpoint: String, tokenId: String, tokenSecret: String) -> Unit
) {

    var endpoint by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultEndpoint))
    }
    var tokenId by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultTokenId))
    }
    var tokenSecret by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultTokenSecret))
    }


    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {

        TextField(
            value = endpoint,
            onValueChange = { endpoint = it },
            label = { Text("Domain") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Text(
            text = "Base URL or domain. eg. https://my.domain.com or my.domain.com",
            modifier = Modifier.padding(16.dp, 0.dp,0.dp,0.dp)
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TextField(
            value = tokenId,
            onValueChange = { tokenId = it },
            label = { Text("Token ID") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        TextField(
            value = tokenSecret,
            onValueChange = { tokenSecret = it },
            label = { Text("Token secret") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(
            onClick = {
                connect(endpoint.text, tokenId.text, tokenSecret.text)
            },
            modifier = Modifier.padding(16.dp).align(Alignment.End)
        ){
            Text(
                text = "Connect",
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

