package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class LessonDto(
    val id: Int,
    val title: String,
    val description: String,
    val ownerId: Int,
    val progress: Int?,
    val cards: List<CardDto>,
)
