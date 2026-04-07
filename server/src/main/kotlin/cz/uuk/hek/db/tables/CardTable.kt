package cz.uuk.hek.db.tables

import org.jetbrains.exposed.sql.Table

object CardTable : Table("card") {
    val id = integer("id").autoIncrement()
    val lessonId = integer("lesson_id").references(LessonTable.id)

    override val primaryKey = PrimaryKey(id)
}
