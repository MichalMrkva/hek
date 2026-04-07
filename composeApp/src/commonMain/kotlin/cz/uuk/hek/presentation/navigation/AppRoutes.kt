package cz.uuk.hek.presentation.navigation

import cz.uuk.hek.domain.model.LessonSummary
import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object Home : AppRoute

    @Serializable
    data class Lesson(val lessonId: Int) : AppRoute
}
