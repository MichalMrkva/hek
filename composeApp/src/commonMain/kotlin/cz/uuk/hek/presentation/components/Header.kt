package cz.uuk.hek.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.uuk.hek.presentation.theme.LocalTheme
import hek.composeapp.generated.resources.Res
import hek.composeapp.generated.resources.logoKotlin
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header(refresh:Unit) {
    val theme = LocalTheme.current
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(Res.drawable.logoKotlin),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(start = 16.dp, top = 36.dp)
                .align(Alignment.CenterStart)
        )
      // Refresh button
      IconButton(
        onClick = theme.toggle,
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .padding(end = 16.dp, top = 36.dp)
      ) {
        Icon(
          imageVector = if (theme.isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
          contentDescription = if (theme.isDark) "Přepnout na světlý režim" else "Přepnout na tmavý režim",
        )
      }

      // Theme change button
        IconButton(
            onClick = theme.toggle,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp, top = 36.dp)
        ) {
            Icon(
                imageVector = if (theme.isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = if (theme.isDark) "Přepnout na světlý režim" else "Přepnout na tmavý režim",
            )
        }
    }
}
