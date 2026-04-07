package cz.uuk.hek.di

import cz.uuk.hek.repository.AnswerRepository
import cz.uuk.hek.repository.CardRepository
import cz.uuk.hek.repository.LessonRepository
import cz.uuk.hek.repository.QuestionRepository
import cz.uuk.hek.repository.UserLessonResultRepository
import cz.uuk.hek.service.CardService
import cz.uuk.hek.service.LessonService
import cz.uuk.hek.service.QuestionService
import cz.uuk.hek.service.UserLessonResultService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AnswerRepository)
    singleOf(::QuestionRepository)
    singleOf(::CardRepository)
    singleOf(::UserLessonResultRepository)
    singleOf(::LessonRepository)
    singleOf(::LessonService)
    singleOf(::CardService)
    singleOf(::QuestionService)
    singleOf(::UserLessonResultService)
}
