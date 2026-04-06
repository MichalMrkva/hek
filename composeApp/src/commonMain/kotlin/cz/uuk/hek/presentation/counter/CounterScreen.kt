package cz.uuk.hek.presentation.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CounterPage(vm: CounterVM = koinViewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    CounterContent(
        state = state,
        onAction = vm::onAction,
    )
}

@Preview
@Composable
internal fun CounterContent(
    state: CounterUiState = CounterUiState(),
    onAction: (CounterUiAction) -> Unit = {},
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "${state.count}", fontSize = 48.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { onAction(CounterUiAction.Decrement) }) { Text("-") }
                Button(onClick = { onAction(CounterUiAction.Increment) }) { Text("+") }
            }
            Button(
                modifier = Modifier.padding(top = 24.dp),
                onClick = { onAction(CounterUiAction.NavigateBack) },
            ) {
                Text("Back")
            }
        }
    }
}
