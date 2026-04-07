package cz.uuk.hek.di

import cz.uuk.hek.presentation.navigation.navigationModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    navigationModule, homeModule,
    lessonModule, repositoryModule,
)
