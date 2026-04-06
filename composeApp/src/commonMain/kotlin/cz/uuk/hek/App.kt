package cz.uuk.hek

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cz.uuk.hek.di.appModules
import cz.uuk.hek.navigation.AppNavGraph
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            MaterialTheme {
                AppNavGraph()
            }
        }
    )
}