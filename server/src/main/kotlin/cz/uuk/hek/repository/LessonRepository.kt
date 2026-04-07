package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.LessonTable
import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class LessonRepository(
    private val cardRepository: CardRepository,
    private val userLessonResultRepository: UserLessonResultRepository,
) {

    fun getAll(userId: Int): List<LessonSummaryDto> = transaction {
        val progress = userLessonResultRepository.getResultsForUser(userId)
        LessonTable.selectAll().map { toSummaryDto(it, progress[it[LessonTable.id]]) }
    }

    fun getById(id: Int, userId: Int): LessonDto? = transaction {
        val progress = userLessonResultRepository.getResult(userId, id)
        LessonTable.selectAll()
            .where { LessonTable.id eq id }
            .singleOrNull()
            ?.let { toDto(it, progress) }
    }

    private fun toSummaryDto(row: ResultRow, progress: Int?) = LessonSummaryDto(
        id = row[LessonTable.id],
        title = row[LessonTable.title],
        description = row[LessonTable.description],
        ownerId = row[LessonTable.ownerId],
        progress = progress,
    )

    private fun toDto(row: ResultRow, progress: Int?): LessonDto {
        val lessonId = row[LessonTable.id]
        return LessonDto(
            id = lessonId,
            title = row[LessonTable.title],
            description = row[LessonTable.description],
            ownerId = row[LessonTable.ownerId],
            progress = progress,
            cards = cardRepository.getByLessonId(lessonId),
        )
    }
}
