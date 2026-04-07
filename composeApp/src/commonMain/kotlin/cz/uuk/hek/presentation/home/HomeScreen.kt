package cz.uuk.hek.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.uuk.hek.domain.vm.HomeVM
import hek.composeapp.generated.resources.Res
import hek.composeapp.generated.resources.logoKotlin
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomePage(vm: HomeVM = koinViewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    HomeContent(
        state = state,
        onAction = vm::onAction,
    )
}

@Preview
@Composable
internal fun HomeContent(
    state: HomeUiState = HomeUiState(),
    onAction: (HomeUiAction) -> Unit = {},
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Header()
            // ---------- TITLE ----------
            /*
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
    ) { paddingValues ->
        if (state.isLoading || state.lessons == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues),
            ) {
                LazyColumn {
                    items(state.lessons) { lesson ->
                        Lesson(
                            title = lesson.title,
                            description = lesson.description,
                            percentage = 10
                        )
                    }
                }
            }

             */

        }
    }
}
