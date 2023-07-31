package dev.mcarr.common.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.mcarr.common.network.API
import kotlinx.coroutines.launch

@Composable
fun Setup(
    defaultEndpoint: String,
    defaultTokenId: String,
    defaultTokenSecret: String,
    defaultDisableHttpsVerification: Boolean,
    connect: (newApi: API) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    var endpoint by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultEndpoint))
    }
    var tokenId by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultTokenId))
    }
    var tokenSecret by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultTokenSecret))
    }
    val (checkedState, onStateChange) = remember { mutableStateOf(defaultDisableHttpsVerification) }
    var saving by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }


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
            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
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
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStateChange(!checkedState) },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = "Disable HTTPS verification",
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = message,
            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
            color = messageColor
        )
        Button(
            onClick = {
                if (!saving) {
                    saving = true
                    message = ""
                    coroutineScope.launch {
                        val api = API(
                            apiUrl = endpoint.text,
                            tokenId = tokenId.text,
                            tokenSecret = tokenSecret.text,
                            disableHttpsVerification = true,
                            testing = true
                        )
                        try {
                            api.getDocsHtml()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            messageColor = Color.Red
                            message = e.localizedMessage
                        }
                        saving = false
                        if (message.isEmpty()) {
                            messageColor = Color.Green
                            message = "Connection successful"
                            connect(api)
                        }
                    }
                }
            },
            modifier = Modifier.padding(16.dp).align(Alignment.End)
        ) {
            if (saving) {
                CircularProgressIndicator(
                    color = Color.Red
                )
            }
            Text(
                text = "Save",
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

