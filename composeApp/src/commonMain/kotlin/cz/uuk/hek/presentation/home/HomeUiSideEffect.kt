package cz.uuk.hek.presentation.home

sealed interface HomeUiSideEffect {
    data object NavigateBack : HomeUiSideEffect
}
