package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.AnswerTable
import cz.uuk.hek.db.tables.QuestionTable
import cz.uuk.hek.dto.CreateQuestionRequest
import cz.uuk.hek.dto.QuestionDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class QuestionRepository(private val answerRepository: AnswerRepository) {

    fun getByCardId(cardId: Int): List<QuestionDto> =
        QuestionTable.selectAll()
            .where { QuestionTable.cardId eq cardId }
            .map { toDto(it) }

    fun create(request: CreateQuestionRequest): QuestionDto = transaction {
        val questionId = QuestionTable.insert {
            it[cardId] = request.cardId
            it[content] = request.content
            it[correctAnswerId] = 0
        }[QuestionTable.id]

        val answerIds = request.answers.map { answerText ->
            AnswerTable.insert {
                it[AnswerTable.questionId] = questionId
                it[AnswerTable.text] = answerText
            }[AnswerTable.id]
        }

        val correctId = answerIds[request.correctAnswerIndex]
        QuestionTable.update({ QuestionTable.id eq questionId }) {
            it[correctAnswerId] = correctId
        }

        toDto(
            QuestionTable.selectAll()
                .where { QuestionTable.id eq questionId }
                .single()
        )
    }

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
