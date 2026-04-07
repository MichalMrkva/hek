package cz.uuk.hek.dto

import kotlinx.serialization.Serializable

@Serializable
data class LessonSummaryDto(
    val id: Int,
    val title: String,
    val description: String,
)
