package cz.uuk.hek.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.uuk.hek.feature.home.data.HomeRepository
import cz.uuk.hek.navigation.AppRoute
import cz.uuk.hek.navigation.NavManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeVM(
    private val repository: HomeRepository,
    private val navManager: NavManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _sideEffect = MutableSharedFlow<HomeUiSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onAction(action: HomeUiAction) {
        when (action) {
            HomeUiAction.Refresh -> refresh()
            HomeUiAction.NavigateToCounter -> navManager.navigateTo(AppRoute.Counter)
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
