package cz.uuk.hek.di

import cz.uuk.hek.presentation.counter.CounterVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val counterModule = module {
    viewModelOf(::CounterVM)
}
