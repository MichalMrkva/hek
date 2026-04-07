package cz.uuk.hek.domain.model

data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    val cards: List<Card>,
)
