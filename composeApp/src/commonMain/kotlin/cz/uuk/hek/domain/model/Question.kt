package cz.uuk.hek.domain.model

data class Question(
    val id: Int,
    val content: String,
    val correctAnswerId: Int,
    val answers: List<Answer>,
)
