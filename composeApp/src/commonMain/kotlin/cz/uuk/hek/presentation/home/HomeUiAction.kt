package cz.uuk.hek.presentation.home

sealed interface HomeUiAction {
    data object Refresh : HomeUiAction
    data object NavigateToCounter : HomeUiAction
}
