package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnswerDto(
    val id: Int,
    val text: String,
)
