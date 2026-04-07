package cz.uuk.hek.presentation.interfaces

import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary

sealed interface DashboardActions {
    data class SetIsLoading(val isLoading: Boolean): DashboardActions
    data object LoadLessons: DashboardActions
    data class OpenLesson(val lesson: Lesson): DashboardActions
}