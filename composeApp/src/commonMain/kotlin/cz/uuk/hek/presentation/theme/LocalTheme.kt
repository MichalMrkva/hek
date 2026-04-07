package cz.uuk.hek.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

data class ThemeState(
    val isDark: Boolean,
    val toggle: () -> Unit,
)

val LocalTheme = compositionLocalOf<ThemeState> {
    error("LocalTheme not provided")
}
