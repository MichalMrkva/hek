package cz.uuk.hek.presentation.navigation

import androidx.navigation.NavController

class NavManager {
    var navController: NavController? = null
        internal set

    fun navigateTo(screen: AppRoute) {
        navController?.navigate(screen)
    }

    fun pop() {
        navController?.popBackStack()
    }

    fun replaceAll(screen: AppRoute) {
        navController?.navigate(screen) {
            popUpTo(0) { inclusive = true }
        }
    }
}
