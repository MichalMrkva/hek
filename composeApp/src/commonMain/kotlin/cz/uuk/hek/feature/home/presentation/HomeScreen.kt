package cz.uuk.hek.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Home")
            Button(onClick = { onAction(HomeUiAction.NavigateToCounter) }) {
                Text("Go to Counter")
            }
        }
    }
}
