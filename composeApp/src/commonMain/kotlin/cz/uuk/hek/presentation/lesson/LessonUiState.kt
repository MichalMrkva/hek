package cz.uuk.hek.presentation.lesson

import androidx.compose.runtime.Immutable
import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Card
import cz.uuk.hek.domain.model.Question

@Immutable
data class LessonUiState(
    val isConfirmOpen: Boolean=false,
    val selectedQuestion: Question?=null,
    val selectedAnswer: Answer?=null,
    val currentCard: Card?=null,
    val cards:List<Card>?=null
)
