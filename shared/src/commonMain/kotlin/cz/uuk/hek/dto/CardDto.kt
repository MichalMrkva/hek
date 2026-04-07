package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardDto(
    val id: Int,
    val questions: List<QuestionDto>,
)
