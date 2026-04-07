package cz.uuk.hek

import cz.uuk.hek.db.seedIfEmpty
import cz.uuk.hek.db.tables.AnswerTable
import cz.uuk.hek.db.tables.CardTable
import cz.uuk.hek.db.tables.LessonTable
import cz.uuk.hek.db.tables.QuestionTable
import cz.uuk.hek.di.appModule
import cz.uuk.hek.service.LessonService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    Database.connect("jdbc:sqlite:hek.db", driver = "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(LessonTable, CardTable, QuestionTable, AnswerTable)
    }
    seedIfEmpty()

    install(Koin) {
        modules(appModule)
    }
    install(ContentNegotiation) {
        json()
    }

    val lessonService: LessonService by inject()

    routing {
        get("/lessons") {
            call.respond(lessonService.getLessons())
        }
        get("/lessons/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val lesson = lessonService.getLessonById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(lesson)
        }
    }
}