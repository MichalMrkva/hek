package cz.uuk.hek.db

import cz.uuk.hek.db.tables.AnswerTable
import cz.uuk.hek.db.tables.CardTable
import cz.uuk.hek.db.tables.LessonTable
import cz.uuk.hek.db.tables.QuestionTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun seedIfEmpty() {
    transaction {
        if (LessonTable.selectAll().count() > 0) return@transaction

        // --- Lesson 1: Základy Kotlinu ---
        val lesson1Id = LessonTable.insert {
            it[title] = "Základy Kotlinu"
            it[description] = "Nauč se základní koncepty jazyka Kotlin."
        }[LessonTable.id]

        val card1Id = CardTable.insert { it[lessonId] = lesson1Id }[CardTable.id]

        // Otázka 1.1
        val q1Id = QuestionTable.insert {
            it[cardId] = card1Id
            it[content] = "Jak se v Kotlinu deklaruje neměnná proměnná?"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val q1a1 = AnswerTable.insert { it[questionId] = q1Id; it[text] = "val" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = q1Id; it[text] = "var" }
        AnswerTable.insert { it[questionId] = q1Id; it[text] = "let" }
        AnswerTable.insert { it[questionId] = q1Id; it[text] = "const" }
        QuestionTable.update({ QuestionTable.id eq q1Id }) { it[correctAnswerId] = q1a1 }

        // Otázka 1.2
        val q2Id = QuestionTable.insert {
            it[cardId] = card1Id
            it[content] = "Která funkce je vstupním bodem Kotlin programu?"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val q2a1 = AnswerTable.insert { it[questionId] = q2Id; it[text] = "main()" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = q2Id; it[text] = "start()" }
        AnswerTable.insert { it[questionId] = q2Id; it[text] = "run()" }
        AnswerTable.insert { it[questionId] = q2Id; it[text] = "init()" }
        QuestionTable.update({ QuestionTable.id eq q2Id }) { it[correctAnswerId] = q2a1 }

        // Otázka 1.3
        val q3Id = QuestionTable.insert {
            it[cardId] = card1Id
            it[content] = "Co je výsledkem výrazu: if (true) \"ano\" else \"ne\" ?"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val q3a1 = AnswerTable.insert { it[questionId] = q3Id; it[text] = "\"ano\"" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = q3Id; it[text] = "\"ne\"" }
        AnswerTable.insert { it[questionId] = q3Id; it[text] = "null" }
        AnswerTable.insert { it[questionId] = q3Id; it[text] = "chyba kompilace" }
        QuestionTable.update({ QuestionTable.id eq q3Id }) { it[correctAnswerId] = q3a1 }

        // --- Lesson 2: Datové struktury ---
        val lesson2Id = LessonTable.insert {
            it[title] = "Datové struktury"
            it[description] = "Procvič si práci se seznamy, mapami a množinami."
        }[LessonTable.id]

        val card2Id = CardTable.insert { it[lessonId] = lesson2Id }[CardTable.id]

        // Otázka 2.1
        val q4Id = QuestionTable.insert {
            it[cardId] = card2Id
            it[content] = "Která kolekce v Kotlinu neumožňuje duplicitní prvky?"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        AnswerTable.insert { it[questionId] = q4Id; it[text] = "List" }
        AnswerTable.insert { it[questionId] = q4Id; it[text] = "Map" }
        val q4a3 = AnswerTable.insert { it[questionId] = q4Id; it[text] = "Set" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = q4Id; it[text] = "Array" }
        QuestionTable.update({ QuestionTable.id eq q4Id }) { it[correctAnswerId] = q4a3 }

        // Otázka 2.2
        val q5Id = QuestionTable.insert {
            it[cardId] = card2Id
            it[content] = "Jaká je časová složitost přístupu k prvku v HashMap?"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val q5a1 = AnswerTable.insert { it[questionId] = q5Id; it[text] = "O(1)" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = q5Id; it[text] = "O(n)" }
        AnswerTable.insert { it[questionId] = q5Id; it[text] = "O(log n)" }
        AnswerTable.insert { it[questionId] = q5Id; it[text] = "O(n²)" }
        QuestionTable.update({ QuestionTable.id eq q5Id }) { it[correctAnswerId] = q5a1 }
    }
}
