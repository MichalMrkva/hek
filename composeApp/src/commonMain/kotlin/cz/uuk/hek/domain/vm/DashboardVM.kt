package cz.uuk.hek.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.presentation.interfaces.DashboardActions
import cz.uuk.hek.presentation.uistates.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardVM(
    private val repository: LessonRepository)
    : ViewModel() {

    private val _uiState= MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState


    init {
        loadLessons()
    }

    fun onDashboardAction(action: DashboardActions){
        when(action)
        {
            is DashboardActions.SetIsLoading -> setLoading(action.isLoading)
            is DashboardActions.LoadLessons -> loadLessons()
            is DashboardActions.OpenLesson -> setCurrentLesson(action.lesson)
        }
    }
    private fun setLoading(isLoading: Boolean)
    {
        _uiState.update { it.copy(isLoading=isLoading) }
    }
    private fun loadLessons()
    {
        viewModelScope.launch {
            val lessons=repository.getLessons()
            _uiState.update { it.copy(lessons=lessons) }
        }
    }

    private fun setCurrentLesson(lesson: Lesson)
    {
        _uiState.update { it.copy(currentLesson = lesson) }
    }

}