package cz.uuk.hek.presentation.lesson

import androidx.lifecycle.ViewModel
import cz.uuk.hek.presentation.navigation.NavManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CounterVM(
    private val navManager: NavManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun onAction(action: CounterUiAction) {
        when (action) {
            CounterUiAction.Increment -> _uiState.update { it.copy(count = it.count + 1) }
            CounterUiAction.Decrement -> _uiState.update { it.copy(count = it.count - 1) }
            CounterUiAction.NavigateBack -> navManager.pop()
        }
    }
}
