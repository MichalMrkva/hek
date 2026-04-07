package cz.uuk.hek.presentation.lesson

import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Card
import cz.uuk.hek.domain.model.LessonSummary
import cz.uuk.hek.domain.model.Question

sealed interface LessonUiAction {
    data class SelectQuestion(val question: Question): LessonUiAction
    data class  SelectAnswer(val answer: Answer): LessonUiAction
    data class Swipe(val direction: SwipeDirection) : LessonUiAction
    data class SetConfirmForm(val isOpen: Boolean): LessonUiAction
    data class Finish(val lesson: LessonSummary): LessonUiAction
}
enum class SwipeDirection { Left, Right }
