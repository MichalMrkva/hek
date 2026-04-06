package cz.uuk.hek.feature.counter.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class CounterUiState(
    val count: Int = 0,
)
