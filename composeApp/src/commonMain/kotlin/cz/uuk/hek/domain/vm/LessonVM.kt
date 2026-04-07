package cz.uuk.hek.domain.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.presentation.home.HomeUiAction
import cz.uuk.hek.presentation.lesson.LessonUiAction
import cz.uuk.hek.presentation.lesson.LessonUiState
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
            is LessonUiAction.SelectCard -> TODO()
            is LessonUiAction.SelectQuestion -> TODO()
        }
    }

    private fun getLessonDetail() = viewModelScope.launch {
        val lesson = lessonRepository.getLesson(route.lessonId)
        _uiState.update {
            it.copy(cards =lesson )
        }
    }
    private fun setConfirmForm(isOpen: Boolean)
    {
        _uiState.update { it.copy(isConfirmOpen = isOpen) }
    }
}