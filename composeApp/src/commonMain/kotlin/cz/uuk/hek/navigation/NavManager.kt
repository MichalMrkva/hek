package cz.uuk.hek.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList

class NavManager(
    val backStack: SnapshotStateList<AppRoute>
) {
    fun navigateTo(screen: AppRoute) {
        backStack.add(screen)
    }
    fun pop() {
        backStack.removeLastOrNull()
    }
    fun replaceAll(screen: AppRoute) {
        backStack.clear()
        backStack.add(screen)
    }
}