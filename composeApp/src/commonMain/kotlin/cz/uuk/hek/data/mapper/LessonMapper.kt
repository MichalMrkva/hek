package cz.uuk.hek.data.mapper

import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Card
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary
import cz.uuk.hek.domain.model.Question
import cz.uuk.hek.dto.AnswerDto
import cz.uuk.hek.dto.CardDto
import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import cz.uuk.hek.dto.QuestionDto

fun LessonSummaryDto.toDomain() = LessonSummary(
    id = id,
    title = title,
    description = description,
)

fun LessonDto.toDomain() = Lesson(
    id = id,
    title = title,
    description = description,
    cards = cards.map { it.toDomain() },
)

fun CardDto.toDomain() = Card(
    id = id,
    questions = questions.map { it.toDomain() },
)

fun QuestionDto.toDomain() = Question(
    id = id,
    content = content,
    correctAnswerId = correctAnswerId,
    answers = answers.map { it.toDomain() },
)

fun AnswerDto.toDomain() = Answer(
    id = id,
    text = text,
)
