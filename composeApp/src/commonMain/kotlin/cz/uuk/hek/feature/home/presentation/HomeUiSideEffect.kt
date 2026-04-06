package cz.uuk.hek.feature.home.presentation

sealed interface HomeUiSideEffect {
    data object NavigateBack : HomeUiSideEffect
}
