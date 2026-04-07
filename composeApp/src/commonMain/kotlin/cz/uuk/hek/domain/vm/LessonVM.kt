package cz.uuk.hek.domain.vm

import androidx.lifecycle.ViewModel
import cz.uuk.hek.presentation.lesson.LessonUiAction
import cz.uuk.hek.presentation.lesson.LessonUiState
import cz.uuk.hek.presentation.navigation.NavManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LessonVM(
    private val navManager: NavManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonUiState())
    val uiState: StateFlow<LessonUiState> = _uiState

    fun onAction(action: LessonUiAction) {
        when (action) {
            is LessonUiAction.Finish -> TODO()
            is LessonUiAction.SetConfirmForm -> TODO()
            is LessonUiAction.SelectAnswer -> TODO()
            is LessonUiAction.SelectCard -> TODO()
            is LessonUiAction.SelectQuestion -> TODO()
        }
    }
}