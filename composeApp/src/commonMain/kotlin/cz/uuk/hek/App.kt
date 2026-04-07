package cz.uuk.hek

import androidx.compose.runtime.Composable
import cz.uuk.hek.di.appModules
import cz.uuk.hek.presentation.navigation.AppNavGraph
import cz.uuk.hek.presentation.theme.HekTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            HekTheme {
                AppNavGraph()
            }
        }
    )
}