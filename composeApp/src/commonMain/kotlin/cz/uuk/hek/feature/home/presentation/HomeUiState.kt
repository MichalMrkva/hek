package cz.uuk.hek.feature.home.presentation

import androidx.compose.runtime.Immutable
import cz.uuk.hek.feature.home.domain.model.HomeModel

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<HomeModel> = emptyList(),
)
