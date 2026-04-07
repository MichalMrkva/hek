package cz.uuk.hek.domain.model

data class Card(
    val id: Int,
    val questions: List<Question>,
)
