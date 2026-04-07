package cz.uuk.hek

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import cz.uuk.hek.di.appModules
import cz.uuk.hek.presentation.navigation.AppNavGraph
import cz.uuk.hek.presentation.theme.HekTheme
import cz.uuk.hek.presentation.theme.LocalTheme
import cz.uuk.hek.presentation.theme.ThemeState
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    val systemDark = isSystemInDarkTheme()
    var isDark by remember { mutableStateOf(systemDark) }

    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            CompositionLocalProvider(
                LocalTheme provides ThemeState(isDark = isDark, toggle = { isDark = !isDark })
            ) {
                HekTheme(darkTheme = isDark) {
                    AppNavGraph()
                }
            }
        }
    )
}