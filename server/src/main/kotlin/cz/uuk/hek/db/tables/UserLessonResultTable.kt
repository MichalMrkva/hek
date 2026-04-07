package cz.uuk.hek.db.tables

import org.jetbrains.exposed.sql.Table

object UserLessonResultTable : Table("user_lesson_result") {
    val userId = integer("user_id")
    val lessonId = integer("lesson_id").references(LessonTable.id)
    val result = integer("result")

    override val primaryKey = PrimaryKey(userId, lessonId)
}
