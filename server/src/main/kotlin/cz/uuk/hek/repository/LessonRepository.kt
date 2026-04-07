package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.LessonTable
import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class LessonRepository(private val cardRepository: CardRepository) {

    fun getAll(): List<LessonSummaryDto> = transaction {
        LessonTable.selectAll().map { toSummaryDto(it) }
    }

    fun getById(id: Int): LessonDto? = transaction {
        LessonTable.selectAll()
            .where { LessonTable.id eq id }
            .singleOrNull()
            ?.let { toDto(it) }
    }

    private fun toSummaryDto(row: ResultRow) = LessonSummaryDto(
        id = row[LessonTable.id],
        title = row[LessonTable.title],
        description = row[LessonTable.description],
    )

    private fun toDto(row: ResultRow): LessonDto {
        val lessonId = row[LessonTable.id]
        return LessonDto(
            id = lessonId,
            title = row[LessonTable.title],
            description = row[LessonTable.description],
            cards = cardRepository.getByLessonId(lessonId),
        )
    }
}
