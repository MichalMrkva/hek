package cz.uuk.hek.presentation.counter

sealed interface CounterUiAction {
    data object Increment : CounterUiAction
    data object Decrement : CounterUiAction
    data object NavigateBack : CounterUiAction
}
