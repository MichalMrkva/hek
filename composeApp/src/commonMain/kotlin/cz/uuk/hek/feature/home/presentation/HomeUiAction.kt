package cz.uuk.hek.feature.home.presentation

sealed interface HomeUiAction {
    data object Refresh : HomeUiAction
    data object NavigateToCounter : HomeUiAction
}
