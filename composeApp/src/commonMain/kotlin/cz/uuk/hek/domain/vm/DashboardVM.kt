package cz.uuk.hek.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.uuk.hek.presentation.interfaces.DashboardActions
import cz.uuk.hek.presentation.uistates.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardVM: ViewModel() {

    private val _uiState= MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState
    //private val repository

    init {
        loadLessons()
    }

    fun onDashboardAction(action: DashboardActions){
        when(action)
        {
            is DashboardActions.SetIsLoading -> setLoading(action.isLoading)
            is DashboardActions.LoadLessons -> loadLessons()
            is DashboardActions.OpenLesson -> setCurrentLesson(action.id)
        }
    }
    private fun setLoading(isLoading: Boolean)
    {
        _uiState.update { it.copy(isLoading=isLoading) }
    }
    private fun loadLessons()
    {
        /*viewModelScope.launch {
            repository.lessons.collect(it->_uistate.update(lessons=it))

        }*/
    }

    private fun setCurrentLesson(id: String)
    {
        _uiState.update { it.copy(currentLesson = id) }
    }

}