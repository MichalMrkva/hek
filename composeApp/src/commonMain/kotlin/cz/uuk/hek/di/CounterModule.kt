package cz.uuk.hek.di

import cz.uuk.hek.domain.vm.LessonVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val counterModule = module {
    viewModelOf(::LessonVM)
}
