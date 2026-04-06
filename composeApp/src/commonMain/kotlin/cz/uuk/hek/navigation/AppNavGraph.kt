package cz.uuk.hek.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import cz.uuk.hek.feature.counter.presentation.CounterPage
import cz.uuk.hek.feature.home.presentation.HomePage
import org.koin.compose.koinInject

@Composable
fun AppNavGraph() {
    val navManager = koinInject<NavManager>()

    NavDisplay(
        backStack = navManager.backStack,
        onBack = { navManager.pop() },
        entryProvider = { route ->
            when (route) {
                AppRoute.Home -> NavEntry(route) { HomePage() }
                AppRoute.Counter -> NavEntry(route) { CounterPage() }
            }
        },
    )
}
