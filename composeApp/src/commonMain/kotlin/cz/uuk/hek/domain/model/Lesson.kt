package cz.uuk.hek.domain.model

data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    val ownerId: Int,
    val progress: Int?,
    val cards: List<Card>,
)