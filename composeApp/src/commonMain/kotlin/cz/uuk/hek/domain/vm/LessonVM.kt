package cz.uuk.hek.domain.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.presentation.home.HomeUiAction
import cz.uuk.hek.presentation.lesson.LessonUiAction
import cz.uuk.hek.presentation.lesson.LessonUiState
import cz.uuk.hek.presentation.lesson.SwipeDirection
import cz.uuk.hek.presentation.navigation.AppRoute
import cz.uuk.hek.presentation.navigation.NavManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LessonVM(
    private val nav: NavManager,
    savedStateHandle: SavedStateHandle,
    private val lessonRepository: LessonRepository,
) : ViewModel() {
    private val route: AppRoute.Lesson = savedStateHandle.toRoute()
    private val _uiState = MutableStateFlow(LessonUiState())
    val uiState: StateFlow<LessonUiState> = _uiState

    init {
        getLessonDetail()
    }

    fun onAction(action: LessonUiAction) {
        when (action) {
            is LessonUiAction.Finish -> TODO()
            is LessonUiAction.SetConfirmForm -> setConfirmForm(action.isOpen)
            is LessonUiAction.SelectAnswer -> TODO()
            is LessonUiAction.SelectQuestion -> TODO()
            is LessonUiAction.Swipe -> swipe(action.direction)
        }
    }

    private fun getLessonDetail() = viewModelScope.launch {
        val lesson = lessonRepository.getLesson(route.lessonId)
        _uiState.update {
            it.copy(lesson =lesson )
        }
    }
    private fun setConfirmForm(isOpen: Boolean)
    {
        _uiState.update { it.copy(isConfirmOpen = isOpen) }
    }
    private fun swipe(direction: SwipeDirection) {
        _uiState.update { state ->
            val lesson = state.lesson ?: return@update state
            val cards = lesson.cards

            val newIndex = when (direction) {
                SwipeDirection.Left -> state.currentIndex - 1
                SwipeDirection.Right -> state.currentIndex + 1
            }
            if (newIndex in cards.indices) {
                state.copy(currentIndex = newIndex)
            } else {
                state.copy(isFinished = true)
            }
        }
    }
}