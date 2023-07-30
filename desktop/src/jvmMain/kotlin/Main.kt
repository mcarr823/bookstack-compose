import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.mcarr.common.App
import dev.mcarr.common.data.AppDatabase
import dev.mcarr.common.network.API
import dev.mcarr.common.ui.navigation.ProvideComponentContext
import dev.mcarr.common.ui.screens.Setup
import kotlinx.coroutines.runBlocking


fun main(){

    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    application {

        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(onCloseRequest = ::exitApplication) {
            currentCompositionLocalContext
            val db = AppDatabase()

            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                        ProvideComponentContext(rootComponentContext) {
                            App(db)
                        }
                    }
                }
            }
        }
    }

}