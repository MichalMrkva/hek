package cz.uuk.hek.presentation.home

import androidx.compose.runtime.Immutable
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary

@Immutable
data class HomeUiState(
    val isLoading: Boolean=false,
    val lessons: List<LessonSummary>?=null,
    val currentLesson: LessonSummary?=null
)
