package cz.uuk.hek.di

import cz.uuk.hek.feature.counter.counterModule
import cz.uuk.hek.feature.home.homeModule
import cz.uuk.hek.navigation.navigationModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    navigationModule,
    homeModule,
    counterModule,
)
