package cz.uuk.hek.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hek.composeapp.generated.resources.Res
import hek.composeapp.generated.resources.logoKotlin
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(Res.drawable.logoKotlin),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(start = 16.dp, top = 36.dp) // ← margin from left
                .align(Alignment.CenterStart)
        )
    }
}