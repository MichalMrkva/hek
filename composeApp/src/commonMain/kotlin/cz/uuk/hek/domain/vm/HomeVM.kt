package cz.uuk.hek.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.uuk.hek.data.LessonRepository
import cz.uuk.hek.domain.model.LessonSummary
import cz.uuk.hek.presentation.home.HomeUiAction
import cz.uuk.hek.presentation.home.HomeUiState
import cz.uuk.hek.presentation.navigation.AppRoute
import cz.uuk.hek.presentation.navigation.NavManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeVM(
    private val repository: LessonRepository,
    private val nav: NavManager
) : ViewModel() {

    private val _uiState= MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    init {
        loadLessons()
    }

    fun onAction(action: HomeUiAction){
        when(action)
        {
            is HomeUiAction.SetIsLoading -> setLoading(action.isLoading)
            is HomeUiAction.LoadLessons -> loadLessons()
            is HomeUiAction.OpenLesson -> setCurrentLesson(action.lesson)
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

    private fun setCurrentLesson(lesson: LessonSummary) {
        nav.navigateTo(AppRoute.Lesson(lesson.id))
    }

}