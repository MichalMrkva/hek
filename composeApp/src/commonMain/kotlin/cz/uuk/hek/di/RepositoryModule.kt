package cz.uuk.hek.di

import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.domain.model.UserMetadata
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    single { UserMetadata(userId = 1) }
    singleOf(::LessonRepository)
}
