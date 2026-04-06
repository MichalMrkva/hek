package cz.uuk.hek.navigation

import androidx.navigation3.runtime.NavKey
sealed interface AppRoute : NavKey {
    data object Home : AppRoute
    data object Counter : AppRoute
}
