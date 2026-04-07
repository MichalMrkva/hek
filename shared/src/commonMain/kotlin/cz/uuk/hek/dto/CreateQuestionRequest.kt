package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateQuestionRequest(
    val cardId: Int,
    val content: String,
    val answers: List<String>,
    val correctAnswerIndex: Int,
)
