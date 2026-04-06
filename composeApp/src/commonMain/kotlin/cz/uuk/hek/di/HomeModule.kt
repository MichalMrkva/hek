package cz.uuk.hek.di

import cz.uuk.hek.presentation.home.HomeVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeVM)
}
