package cz.uuk.hek.db.tables

import org.jetbrains.exposed.sql.Table

object QuestionTable : Table("question") {
    val id = integer("id").autoIncrement()
    val cardId = integer("card_id").references(CardTable.id)
    val content = text("content")
    val correctAnswerId = integer("correct_answer_id")

    override val primaryKey = PrimaryKey(id)
}
