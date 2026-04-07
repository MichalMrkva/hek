package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.QuestionTable
import cz.uuk.hek.dto.QuestionDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class QuestionRepository(private val answerRepository: AnswerRepository) {

    fun getByCardId(cardId: Int): List<QuestionDto> =
        QuestionTable.selectAll()
            .where { QuestionTable.cardId eq cardId }
            .map { toDto(it) }

    fun toDto(row: ResultRow): QuestionDto {
        val questionId = row[QuestionTable.id]
        return QuestionDto(
            id = questionId,
            content = row[QuestionTable.content],
            correctAnswerId = row[QuestionTable.correctAnswerId],
            answers = answerRepository.getByQuestionId(questionId),
        )
    }
}
