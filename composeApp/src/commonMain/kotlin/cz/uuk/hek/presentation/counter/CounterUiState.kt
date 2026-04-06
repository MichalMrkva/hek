package cz.uuk.hek.presentation.counter

import androidx.compose.runtime.Immutable

@Immutable
data class CounterUiState(
    val count: Int = 0,
)
