package cz.uuk.hek.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF794FF4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFA390DC),
    onPrimaryContainer = Color(0xFFE7E0EC),
    secondary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFA390DC),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),
    background = Color(0xFFDCD5FA),
    onBackground = Color(0xFF36343e),
    surface = Color(0xFFA390DC),
    onSurface = Color(0xFF36343e),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFFFFFFFF),
    outline = Color(0xFFD9D9D9), //graph background
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF794FF4), // graph progression
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF524F5F),
    onPrimaryContainer = Color(0xFF5684B6),
    secondary = Color(0xFFDDE368),
    onSecondary = Color(0xFFFF6611),
    secondaryContainer = Color(0xFF524F5F),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFF18FAA),
    background = Color(0xFF36343E), // background
    onBackground = Color(0xFFFFFFFF), //Text
    surface = Color(0xFF29262E),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF524F5F),
    onSurfaceVariant = Color(0xFFD9D9D9),
    outline = Color(0xFFD9D9D9),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
)

@Composable
fun HekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
