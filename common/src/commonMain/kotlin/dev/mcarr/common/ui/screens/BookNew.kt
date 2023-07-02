package dev.mcarr.common.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun BookNew(
    bookId: Int?,
    defaultName: String,
    defaultDescription: String,
    submit: (bookId: Int?, name: String, description: String) -> Unit
) {

    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultName, TextRange(1, 255)))
    }
    var description by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(defaultDescription, TextRange(0, 1_000)))
    }


    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Text(
            text = "Maximum 255 characters in length",
            modifier = Modifier.padding(16.dp, 0.dp,0.dp,0.dp)
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Text(
            text = "Maximum 1,000 characters in length",
            modifier = Modifier.padding(16.dp, 0.dp,0.dp,0.dp)
        )
        Button(
            onClick = {
                submit(bookId, name.text, description.text)
            },
            modifier = Modifier.padding(16.dp).align(Alignment.End)
        ){
            Text(
                text = "Save",
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Composable
@Preview
fun PreviewBookNew(){
    Surface {
        BookNew(
            bookId = null,
            defaultName = "",
            defaultDescription = "",
            submit = {_, _, _ ->}
        )
    }
}