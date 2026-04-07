package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val id: Int,
    val content: String,
    val correctAnswerId: Int,
    val answers: List<AnswerDto>,
)
