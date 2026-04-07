package cz.uuk.hek.presentation.lesson

import androidx.compose.runtime.Immutable

@Immutable
data class CounterUiState(
    val count: Int = 0,
)
