package cz.uuk.hek.di

import cz.uuk.hek.repository.AnswerRepository
import cz.uuk.hek.repository.CardRepository
import cz.uuk.hek.repository.LessonRepository
import cz.uuk.hek.repository.QuestionRepository
import cz.uuk.hek.service.CardService
import cz.uuk.hek.service.LessonService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AnswerRepository)
    singleOf(::QuestionRepository)
    singleOf(::CardRepository)
    singleOf(::LessonRepository)
    singleOf(::LessonService)
    singleOf(::CardService)
}
