package cz.uuk.hek.db.tables

import org.jetbrains.exposed.sql.Table

object AnswerTable : Table("answer") {
    val id = integer("id").autoIncrement()
    val questionId = integer("question_id").references(QuestionTable.id)
    val text = text("text")

    override val primaryKey = PrimaryKey(id)
}
