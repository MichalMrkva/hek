package cz.uuk.hek.repository

import cz.uuk.hek.db.tables.UserLessonResultTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.upsert

class UserLessonResultRepository {

    fun upsert(userId: Int, lessonId: Int, result: Int) = transaction {
        UserLessonResultTable.upsert {
            it[UserLessonResultTable.userId] = userId
            it[UserLessonResultTable.lessonId] = lessonId
            it[UserLessonResultTable.result] = result
        }
    }

    fun getResult(userId: Int, lessonId: Int): Int? = transaction {
        UserLessonResultTable.selectAll()
            .where { (UserLessonResultTable.userId eq userId) and (UserLessonResultTable.lessonId eq lessonId) }
            .singleOrNull()
            ?.get(UserLessonResultTable.result)
    }

    fun getResultsForUser(userId: Int): Map<Int, Int> = transaction {
        UserLessonResultTable.selectAll()
            .where { UserLessonResultTable.userId eq userId }
            .associate { it[UserLessonResultTable.lessonId] to it[UserLessonResultTable.result] }
    }
}
