package cz.uuk.hek.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.uuk.hek.presentation.lesson.LessonPage
import cz.uuk.hek.presentation.home.HomePage
import org.koin.compose.koinInject

@Composable
fun AppNavGraph() {
    val navManager = koinInject<NavManager>()
    val navController = rememberNavController()

    DisposableEffect(navController) {
        navManager.navController = navController
        onDispose { navManager.navController = null }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoute.Home,
    ) {
        composable<AppRoute.Home> { HomePage() }
        composable<AppRoute.Lesson> { LessonPage() }
    }
}
