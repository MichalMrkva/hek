package cz.uuk.hek.presentation.navigation

import org.koin.dsl.module

val navigationModule = module {
    single { NavManager() }
}
