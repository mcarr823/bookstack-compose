import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.mcarr.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
