package cz.uuk.hek.domain.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Question
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
            is LessonUiAction.Complete -> complete()
            is LessonUiAction.Finish -> finish()
            is LessonUiAction.Back -> nav.pop()
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
    private fun selectQuestion(question: Question) {
        _uiState.update { state ->
            val newSelected = if (state.selectedQuestion?.id == question.id) null else question
            state.copy(selectedQuestion = newSelected)
        }
    }

    private fun selectAnswer(answer: Answer) {
        _uiState.update { state ->
            val question = state.selectedQuestion ?: return@update state
            state.copy(
                answeredQuestions = state.answeredQuestions + (question.id to answer),
                selectedQuestion = null,
            )
        }
    }

    private fun setConfirmForm(isOpen: Boolean) {
        _uiState.update { it.copy(isConfirmOpen = isOpen) }
    }

    private fun complete() {
        val state = _uiState.value
        val allQuestions = state.lesson?.cards?.flatMap { it.questions } ?: emptyList()
        val correct = allQuestions.count { q -> state.answeredQuestions[q.id]?.id == q.correctAnswerId }
        val score = if (allQuestions.isEmpty()) 0 else (correct * 100) / allQuestions.size
        _uiState.update { it.copy(result = score) }
    }

    private fun finish() = viewModelScope.launch {
        val state = _uiState.value
        val score = state.result ?: 0
        lessonRepository.completeLesson(route.lessonId, score)
        nav.pop()
    }
}