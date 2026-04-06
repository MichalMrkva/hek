package cz.uuk.hek.feature.counter

import cz.uuk.hek.feature.counter.presentation.CounterVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val counterModule = module {
    viewModelOf(::CounterVM)
}
