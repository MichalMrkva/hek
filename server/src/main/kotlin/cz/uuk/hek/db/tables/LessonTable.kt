package cz.uuk.hek.db.tables

import org.jetbrains.exposed.sql.Table

object LessonTable : Table("lesson") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val description = text("description")
    val ownerId = integer("owner_id").default(0)

    override val primaryKey = PrimaryKey(id)
}
