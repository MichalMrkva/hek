package cz.uuk.hek.navigation

import androidx.compose.runtime.mutableStateListOf
import org.koin.dsl.module

val navigationModule = module {
    single { NavManager(mutableStateListOf(AppRoute.Home)) }
}
