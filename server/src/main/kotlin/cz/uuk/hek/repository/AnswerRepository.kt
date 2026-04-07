package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.AnswerTable
import cz.uuk.hek.dto.AnswerDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class AnswerRepository {

    fun getByQuestionId(questionId: Int): List<AnswerDto> =
        AnswerTable.selectAll()
            .where { AnswerTable.questionId eq questionId }
            .map { toDto(it) }

    private fun toDto(row: ResultRow) = AnswerDto(
        id = row[AnswerTable.id],
        text = row[AnswerTable.text],
    )
}
