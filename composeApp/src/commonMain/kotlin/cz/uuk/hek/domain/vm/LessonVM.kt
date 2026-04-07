package cz.uuk.hek.domain.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Question
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
            is LessonUiAction.Finish -> finish()
            is LessonUiAction.SetConfirmForm -> setConfirmForm(action.isOpen)
            is LessonUiAction.SelectAnswer -> selectAnswer(action.answer)
            is LessonUiAction.SelectQuestion -> selectQuestion(action.question)
        }
    }

    private fun getLessonDetail() = viewModelScope.launch {
        val lesson = lessonRepository.getLesson(route.lessonId)
        _uiState.update {
            it.copy(lesson =lesson )
        }
    }
    private fun setConfirmForm(isOpen: Boolean) {
        _uiState.update { it.copy(isConfirmOpen = isOpen) }
    }

    private fun finish() = viewModelScope.launch {
        lessonRepository.completeLesson(route.lessonId, 0)
        nav.pop()
    }
    private fun selectAnswer(answer: Answer) {
        _uiState.update { state ->
            state.copy(
                selectedAnswer = answer,
                pickedAnswers = state.pickedAnswers + answer
            )
        }
    }
    private fun selectQuestion(question: Question){
        _uiState.update { it.copy(selectedQuestion = question) }
    }
}