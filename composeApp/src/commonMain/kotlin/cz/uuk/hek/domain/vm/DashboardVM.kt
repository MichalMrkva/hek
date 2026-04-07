package cz.uuk.hek.domain.vm

import cz.uuk.hek.presentation.interfaces.DashboardActions
import cz.uuk.hek.presentation.uistates.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DashboardVM {

    private val _uiState= MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun onDashboardAction(action: DashboardActions){
        when(action)
        {
            is DashboardActions.SetIsLoading -> setLoading(action.isLoading)
            is DashboardActions.LoadLessons -> TODO()
            is DashboardActions.OpenLesson -> TODO()
        }
    }
    private fun setLoading(isLoading: Boolean)
    {
        _uiState.update { it.copy(isLoading=isLoading) }
    }
    private fun loadLessons()
    {

    }

    private fun setCurrentLesson(id: String)
    {
        _uiState.update { it.copy(currentLesson = id) }
    }

}