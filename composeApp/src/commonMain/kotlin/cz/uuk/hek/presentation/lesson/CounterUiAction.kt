package cz.uuk.hek.presentation.lesson

sealed interface CounterUiAction {
    data object Increment : CounterUiAction
    data object Decrement : CounterUiAction
    data object NavigateBack : CounterUiAction
}
