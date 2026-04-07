package cz.uuk.hek.di

import cz.uuk.hek.data.LessonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::LessonRepository)
}
