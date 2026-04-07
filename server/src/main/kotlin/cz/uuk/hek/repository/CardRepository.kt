package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.CardTable
import cz.uuk.hek.dto.CardDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CardRepository(private val questionRepository: QuestionRepository) {

    fun getByLessonId(lessonId: Int): List<CardDto> = transaction {
        CardTable.selectAll()
            .where { CardTable.lessonId eq lessonId }
            .map { toDto(it) }
    }

    fun getById(id: Int): CardDto? = transaction {
        CardTable.selectAll()
            .where { CardTable.id eq id }
            .singleOrNull()
            ?.let { toDto(it) }
    }

    fun toDto(row: ResultRow): CardDto {
        val cardId = row[CardTable.id]
        return CardDto(
            id = cardId,
            questions = questionRepository.getByCardId(cardId),
        )
    }
}
