package cz.uuk.hek.presentation.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import cz.uuk.hek.domain.vm.LessonVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LessonPage(vm: LessonVM = koinViewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    LessonContent(
        state = state,
        onAction = vm::onAction,
    )
}


@Preview
@Composable
internal fun LessonContent(
    state: LessonUiState = LessonUiState(),
    onAction: (LessonUiAction) -> Unit = {},
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if(state.lesson == null) {
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
                val pagerState = rememberPagerState(pageCount = { state.lesson.cards.size + 1 })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { index ->
                    if(index < state.lesson.cards.size) {

                    }
                    else {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Button(
                                onClick = {
                                    onAction(LessonUiAction.Finish)
                                }
                            ) {
                                Text("Finish")
                            }
                        }
                    }
                }
            }
        }
    }
}
