package cz.uuk.hek.presentation.home

import cz.uuk.hek.domain.model.Lesson

sealed interface HomeUiAction {
    data class SetIsLoading(val isLoading: Boolean): HomeUiAction
    data object LoadLessons: HomeUiAction
    data class OpenLesson(val lesson: Lesson): HomeUiAction
}
