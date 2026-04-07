package cz.uuk.hek.presentation.lesson

import androidx.compose.runtime.Immutable
import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.Question

@Immutable
data class LessonUiState(
    val isConfirmOpen: Boolean=false,
    val selectedQuestion: Question?=null,
    val selectedAnswer: Answer?=null,
    val lesson: Lesson?=null,
    val currentIndex:Int=0,
    val isFinished: Boolean=false,
    val pickedAnswers: List<Answer> = emptyList()
)
