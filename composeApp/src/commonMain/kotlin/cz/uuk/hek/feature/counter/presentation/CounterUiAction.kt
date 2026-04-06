package cz.uuk.hek.feature.counter.presentation

sealed interface CounterUiAction {
    data object Increment : CounterUiAction
    data object Decrement : CounterUiAction
    data object NavigateBack : CounterUiAction
}
