package cz.uuk.hek.presentation.uistates

import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary

data class DashboardUiState(
    val isLoading: Boolean=false,
    val lessons: List<LessonSummary>?=null,
    val currentLesson: Lesson?=null

)
