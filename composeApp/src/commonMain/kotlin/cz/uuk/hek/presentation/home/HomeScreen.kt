package cz.uuk.hek.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onVisibilityChangedNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.uuk.hek.domain.vm.HomeVM
import cz.uuk.hek.presentation.components.Header
import cz.uuk.hek.presentation.components.Lesson
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
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(refresh=onAction(HomeUiAction.LoadLessons))
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
              // Refresh button
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
              ) {
                Button(onClick = {onAction(HomeUiAction.LoadLessons) }) {
                  Text("Refresh")
                }
              }

                LazyColumn {
                    items(state.lessons) { lesson ->
                        Lesson(
                            title = lesson.title,
                            description = lesson.description,
                            percentage = lesson.progress ?: 0,
                            modifier = Modifier
                                .clickable() {
                                    onAction(HomeUiAction.OpenLesson(lesson))
                                }
                        )
                    }
                }
            }
        }
    }
}
