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

        // ─────────────────────────────────────────────
        // Lesson 1: Základy Kotlinu
        // ─────────────────────────────────────────────
        val lesson1Id = LessonTable.insert {
            it[title] = "Základy Kotlinu"
            it[description] = "Proměnné, typy, podmínky a smyčky."
        }[LessonTable.id]

        // Card 1 – val vs var
        val l1c1 = CardTable.insert { it[lessonId] = lesson1Id }[CardTable.id]

        val l1c1q1 = QuestionTable.insert {
            it[cardId] = l1c1
            it[content] = "@@@ name = \"Kotlin\"\nprintln(\"Hello, \$name!\")"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c1q1a1 = AnswerTable.insert { it[questionId] = l1c1q1; it[text] = "val" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c1q1; it[text] = "var" }
        AnswerTable.insert { it[questionId] = l1c1q1; it[text] = "let" }
        AnswerTable.insert { it[questionId] = l1c1q1; it[text] = "const" }
        QuestionTable.update({ QuestionTable.id eq l1c1q1 }) { it[correctAnswerId] = l1c1q1a1 }

        val l1c1q2 = QuestionTable.insert {
            it[cardId] = l1c1
            it[content] = "@@@ count = 0\ncount += 1\nprintln(count)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c1q2a1 = AnswerTable.insert { it[questionId] = l1c1q2; it[text] = "var" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c1q2; it[text] = "val" }
        AnswerTable.insert { it[questionId] = l1c1q2; it[text] = "let" }
        AnswerTable.insert { it[questionId] = l1c1q2; it[text] = "const" }
        QuestionTable.update({ QuestionTable.id eq l1c1q2 }) { it[correctAnswerId] = l1c1q2a1 }

        // Card 2 – when expression
        val l1c2 = CardTable.insert { it[lessonId] = lesson1Id }[CardTable.id]

        val l1c2q1 = QuestionTable.insert {
            it[cardId] = l1c2
            it[content] = "val result = @@@ (x) {\n    1    -> \"one\"\n    2    -> \"two\"\n    else -> \"other\"\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c2q1a1 = AnswerTable.insert { it[questionId] = l1c2q1; it[text] = "when" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c2q1; it[text] = "if" }
        AnswerTable.insert { it[questionId] = l1c2q1; it[text] = "switch" }
        AnswerTable.insert { it[questionId] = l1c2q1; it[text] = "match" }
        QuestionTable.update({ QuestionTable.id eq l1c2q1 }) { it[correctAnswerId] = l1c2q1a1 }

        val l1c2q2 = QuestionTable.insert {
            it[cardId] = l1c2
            it[content] = "fun sum(n: Int): Int {\n    var result = 0\n    for (i in 1..@@@) {\n        result += i\n    }\n    return result\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c2q2a1 = AnswerTable.insert { it[questionId] = l1c2q2; it[text] = "n" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c2q2; it[text] = "n - 1" }
        AnswerTable.insert { it[questionId] = l1c2q2; it[text] = "n + 1" }
        AnswerTable.insert { it[questionId] = l1c2q2; it[text] = "n.size" }
        QuestionTable.update({ QuestionTable.id eq l1c2q2 }) { it[correctAnswerId] = l1c2q2a1 }

        // Card 3 – null safety
        val l1c3 = CardTable.insert { it[lessonId] = lesson1Id }[CardTable.id]

        val l1c3q1 = QuestionTable.insert {
            it[cardId] = l1c3
            it[content] = "val name: String@@@ = null\nprintln(name?.length)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c3q1a1 = AnswerTable.insert { it[questionId] = l1c3q1; it[text] = "?" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c3q1; it[text] = "!" }
        AnswerTable.insert { it[questionId] = l1c3q1; it[text] = "??" }
        AnswerTable.insert { it[questionId] = l1c3q1; it[text] = "" }
        QuestionTable.update({ QuestionTable.id eq l1c3q1 }) { it[correctAnswerId] = l1c3q1a1 }

        val l1c3q2 = QuestionTable.insert {
            it[cardId] = l1c3
            it[content] = "val input: String? = null\nval len = input?.length @@@ 0\nprintln(len)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l1c3q2a1 = AnswerTable.insert { it[questionId] = l1c3q2; it[text] = "?:" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l1c3q2; it[text] = "||" }
        AnswerTable.insert { it[questionId] = l1c3q2; it[text] = "??" }
        AnswerTable.insert { it[questionId] = l1c3q2; it[text] = "!!" }
        QuestionTable.update({ QuestionTable.id eq l1c3q2 }) { it[correctAnswerId] = l1c3q2a1 }

        // ─────────────────────────────────────────────
        // Lesson 2: Funkce a lambdy
        // ─────────────────────────────────────────────
        val lesson2Id = LessonTable.insert {
            it[title] = "Funkce a lambdy"
            it[description] = "Funkce, výchozí parametry, lambda výrazy."
        }[LessonTable.id]

        // Card 1 – deklarace funkcí
        val l2c1 = CardTable.insert { it[lessonId] = lesson2Id }[CardTable.id]

        val l2c1q1 = QuestionTable.insert {
            it[cardId] = l2c1
            it[content] = "fun greet(name: String = @@@): String {\n    return \"Hello, \$name!\"\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l2c1q1a1 = AnswerTable.insert { it[questionId] = l2c1q1; it[text] = "\"World\"" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l2c1q1; it[text] = "null" }
        AnswerTable.insert { it[questionId] = l2c1q1; it[text] = "\"\"" }
        AnswerTable.insert { it[questionId] = l2c1q1; it[text] = "name" }
        QuestionTable.update({ QuestionTable.id eq l2c1q1 }) { it[correctAnswerId] = l2c1q1a1 }

        val l2c1q2 = QuestionTable.insert {
            it[cardId] = l2c1
            it[content] = "fun add(a: Int, b: Int)@@@ Int = a + b"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l2c1q2a1 = AnswerTable.insert { it[questionId] = l2c1q2; it[text] = ":" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l2c1q2; it[text] = "->" }
        AnswerTable.insert { it[questionId] = l2c1q2; it[text] = "=" }
        AnswerTable.insert { it[questionId] = l2c1q2; it[text] = "::" }
        QuestionTable.update({ QuestionTable.id eq l2c1q2 }) { it[correctAnswerId] = l2c1q2a1 }

        // Card 2 – lambda a higher-order funkce
        val l2c2 = CardTable.insert { it[lessonId] = lesson2Id }[CardTable.id]

        val l2c2q1 = QuestionTable.insert {
            it[cardId] = l2c2
            it[content] = "val double: (Int) -> Int = @@@ x -> x * 2 }"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l2c2q1a1 = AnswerTable.insert { it[questionId] = l2c2q1; it[text] = "{" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l2c2q1; it[text] = "(" }
        AnswerTable.insert { it[questionId] = l2c2q1; it[text] = "[" }
        AnswerTable.insert { it[questionId] = l2c2q1; it[text] = "<" }
        QuestionTable.update({ QuestionTable.id eq l2c2q1 }) { it[correctAnswerId] = l2c2q1a1 }

        val l2c2q2 = QuestionTable.insert {
            it[cardId] = l2c2
            it[content] = "val nums = listOf(1, 2, 3, 4, 5)\nval evens = nums.@@@ { it % 2 == 0 }\nprintln(evens)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l2c2q2a1 = AnswerTable.insert { it[questionId] = l2c2q2; it[text] = "filter" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l2c2q2; it[text] = "map" }
        AnswerTable.insert { it[questionId] = l2c2q2; it[text] = "find" }
        AnswerTable.insert { it[questionId] = l2c2q2; it[text] = "forEach" }
        QuestionTable.update({ QuestionTable.id eq l2c2q2 }) { it[correctAnswerId] = l2c2q2a1 }

        // ─────────────────────────────────────────────
        // Lesson 3: Třídy a OOP
        // ─────────────────────────────────────────────
        val lesson3Id = LessonTable.insert {
            it[title] = "Třídy a OOP"
            it[description] = "Data classes, dědičnost a přepisování metod."
        }[LessonTable.id]

        // Card 1 – data class
        val l3c1 = CardTable.insert { it[lessonId] = lesson3Id }[CardTable.id]

        val l3c1q1 = QuestionTable.insert {
            it[cardId] = l3c1
            it[content] = "@@@ class Point(val x: Int, val y: Int)\n\nval p = Point(1, 2)\nval q = p.copy(x = 5)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l3c1q1a1 = AnswerTable.insert { it[questionId] = l3c1q1; it[text] = "data" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l3c1q1; it[text] = "open" }
        AnswerTable.insert { it[questionId] = l3c1q1; it[text] = "abstract" }
        AnswerTable.insert { it[questionId] = l3c1q1; it[text] = "sealed" }
        QuestionTable.update({ QuestionTable.id eq l3c1q1 }) { it[correctAnswerId] = l3c1q1a1 }

        val l3c1q2 = QuestionTable.insert {
            it[cardId] = l3c1
            it[content] = "data class User(val name: String, val age: Int)\n\nval u = User(\"Alice\", 30)\nval (n, a) = u\nprintln(@@@)"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l3c1q2a1 = AnswerTable.insert { it[questionId] = l3c1q2; it[text] = "n" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l3c1q2; it[text] = "u.name" }
        AnswerTable.insert { it[questionId] = l3c1q2; it[text] = "name" }
        AnswerTable.insert { it[questionId] = l3c1q2; it[text] = "u[0]" }
        QuestionTable.update({ QuestionTable.id eq l3c1q2 }) { it[correctAnswerId] = l3c1q2a1 }

        // Card 2 – dědičnost
        val l3c2 = CardTable.insert { it[lessonId] = lesson3Id }[CardTable.id]

        val l3c2q1 = QuestionTable.insert {
            it[cardId] = l3c2
            it[content] = "@@@ class Animal(val name: String) {\n    open fun speak(): String = \"...\"\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l3c2q1a1 = AnswerTable.insert { it[questionId] = l3c2q1; it[text] = "open" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l3c2q1; it[text] = "data" }
        AnswerTable.insert { it[questionId] = l3c2q1; it[text] = "abstract" }
        AnswerTable.insert { it[questionId] = l3c2q1; it[text] = "final" }
        QuestionTable.update({ QuestionTable.id eq l3c2q1 }) { it[correctAnswerId] = l3c2q1a1 }

        val l3c2q2 = QuestionTable.insert {
            it[cardId] = l3c2
            it[content] = "class Dog(name: String) : Animal(name) {\n    @@@ fun speak(): String = \"Woof!\"\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l3c2q2a1 = AnswerTable.insert { it[questionId] = l3c2q2; it[text] = "override" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l3c2q2; it[text] = "open" }
        AnswerTable.insert { it[questionId] = l3c2q2; it[text] = "super" }
        AnswerTable.insert { it[questionId] = l3c2q2; it[text] = "final" }
        QuestionTable.update({ QuestionTable.id eq l3c2q2 }) { it[correctAnswerId] = l3c2q2a1 }

        // ─────────────────────────────────────────────
        // Lesson 4: Kolekce
        // ─────────────────────────────────────────────
        val lesson4Id = LessonTable.insert {
            it[title] = "Kolekce"
            it[description] = "Práce s listy, mapami a funkcionálními operacemi."
        }[LessonTable.id]

        // Card 1 – map / filter / reduce
        val l4c1 = CardTable.insert { it[lessonId] = lesson4Id }[CardTable.id]

        val l4c1q1 = QuestionTable.insert {
            it[cardId] = l4c1
            it[content] = "val nums = listOf(1, 2, 3)\nval doubled = nums.@@@ { it * 2 }\n// [2, 4, 6]"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l4c1q1a1 = AnswerTable.insert { it[questionId] = l4c1q1; it[text] = "map" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l4c1q1; it[text] = "filter" }
        AnswerTable.insert { it[questionId] = l4c1q1; it[text] = "forEach" }
        AnswerTable.insert { it[questionId] = l4c1q1; it[text] = "reduce" }
        QuestionTable.update({ QuestionTable.id eq l4c1q1 }) { it[correctAnswerId] = l4c1q1a1 }

        val l4c1q2 = QuestionTable.insert {
            it[cardId] = l4c1
            it[content] = "val nums = listOf(1, 2, 3, 4, 5)\nval sum = nums.@@@ { acc, n -> acc + n }\nprintln(sum) // 15"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l4c1q2a1 = AnswerTable.insert { it[questionId] = l4c1q2; it[text] = "reduce" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l4c1q2; it[text] = "map" }
        AnswerTable.insert { it[questionId] = l4c1q2; it[text] = "filter" }
        AnswerTable.insert { it[questionId] = l4c1q2; it[text] = "sumOf" }
        QuestionTable.update({ QuestionTable.id eq l4c1q2 }) { it[correctAnswerId] = l4c1q2a1 }

        // Card 2 – mapOf / groupBy
        val l4c2 = CardTable.insert { it[lessonId] = lesson4Id }[CardTable.id]

        val l4c2q1 = QuestionTable.insert {
            it[cardId] = l4c2
            it[content] = "val scores = @@@Of(\n    \"Alice\" to 95,\n    \"Bob\"   to 87\n)\nprintln(scores[\"Alice\"])"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l4c2q1a1 = AnswerTable.insert { it[questionId] = l4c2q1; it[text] = "map" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l4c2q1; it[text] = "list" }
        AnswerTable.insert { it[questionId] = l4c2q1; it[text] = "set" }
        AnswerTable.insert { it[questionId] = l4c2q1; it[text] = "mutable" }
        QuestionTable.update({ QuestionTable.id eq l4c2q1 }) { it[correctAnswerId] = l4c2q1a1 }

        val l4c2q2 = QuestionTable.insert {
            it[cardId] = l4c2
            it[content] = "val words = listOf(\"hi\", \"hello\", \"hey\", \"bye\")\nval grouped = words.@@@ { it.first() }\n// {h=[hi, hello, hey], b=[bye]}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l4c2q2a1 = AnswerTable.insert { it[questionId] = l4c2q2; it[text] = "groupBy" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l4c2q2; it[text] = "sortBy" }
        AnswerTable.insert { it[questionId] = l4c2q2; it[text] = "partition" }
        AnswerTable.insert { it[questionId] = l4c2q2; it[text] = "associate" }
        QuestionTable.update({ QuestionTable.id eq l4c2q2 }) { it[correctAnswerId] = l4c2q2a1 }

        // ─────────────────────────────────────────────
        // Lesson 5: Coroutines
        // ─────────────────────────────────────────────
        val lesson5Id = LessonTable.insert {
            it[title] = "Coroutines"
            it[description] = "Asynchronní kód s Kotlin coroutines."
        }[LessonTable.id]

        // Card 1 – suspend / launch / async
        val l5c1 = CardTable.insert { it[lessonId] = lesson5Id }[CardTable.id]

        val l5c1q1 = QuestionTable.insert {
            it[cardId] = l5c1
            it[content] = "@@@ fun fetchData(): String {\n    delay(1000)\n    return \"data\"\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l5c1q1a1 = AnswerTable.insert { it[questionId] = l5c1q1; it[text] = "suspend" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l5c1q1; it[text] = "async" }
        AnswerTable.insert { it[questionId] = l5c1q1; it[text] = "coroutine" }
        AnswerTable.insert { it[questionId] = l5c1q1; it[text] = "launch" }
        QuestionTable.update({ QuestionTable.id eq l5c1q1 }) { it[correctAnswerId] = l5c1q1a1 }

        val l5c1q2 = QuestionTable.insert {
            it[cardId] = l5c1
            it[content] = "val result = @@@ {\n    fetchData()\n}.await()"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l5c1q2a1 = AnswerTable.insert { it[questionId] = l5c1q2; it[text] = "async" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l5c1q2; it[text] = "launch" }
        AnswerTable.insert { it[questionId] = l5c1q2; it[text] = "runBlocking" }
        AnswerTable.insert { it[questionId] = l5c1q2; it[text] = "withContext" }
        QuestionTable.update({ QuestionTable.id eq l5c1q2 }) { it[correctAnswerId] = l5c1q2a1 }

        // Card 2 – withContext / Dispatchers
        val l5c2 = CardTable.insert { it[lessonId] = lesson5Id }[CardTable.id]

        val l5c2q1 = QuestionTable.insert {
            it[cardId] = l5c2
            it[content] = "suspend fun loadFromDisk(): String {\n    return withContext(Dispatchers.@@@) {\n        File(\"data.txt\").readText()\n    }\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l5c2q1a1 = AnswerTable.insert { it[questionId] = l5c2q1; it[text] = "IO" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l5c2q1; it[text] = "Main" }
        AnswerTable.insert { it[questionId] = l5c2q1; it[text] = "Default" }
        AnswerTable.insert { it[questionId] = l5c2q1; it[text] = "Unconfined" }
        QuestionTable.update({ QuestionTable.id eq l5c2q1 }) { it[correctAnswerId] = l5c2q1a1 }

        val l5c2q2 = QuestionTable.insert {
            it[cardId] = l5c2
            it[content] = "viewModelScope.@@@ {\n    val data = fetchData()\n    _uiState.value = data\n}"
            it[correctAnswerId] = 0
        }[QuestionTable.id]
        val l5c2q2a1 = AnswerTable.insert { it[questionId] = l5c2q2; it[text] = "launch" }[AnswerTable.id]
        AnswerTable.insert { it[questionId] = l5c2q2; it[text] = "async" }
        AnswerTable.insert { it[questionId] = l5c2q2; it[text] = "collect" }
        AnswerTable.insert { it[questionId] = l5c2q2; it[text] = "run" }
        QuestionTable.update({ QuestionTable.id eq l5c2q2 }) { it[correctAnswerId] = l5c2q2a1 }
    }
}
