package cz.uuk.hek.feature.home

import cz.uuk.hek.feature.home.data.HomeRepository
import cz.uuk.hek.feature.home.presentation.HomeVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    single { HomeRepository() }
    viewModelOf(::HomeVM)
}
