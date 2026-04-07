package cz.uuk.hek

import cz.uuk.hek.db.seedIfEmpty
import cz.uuk.hek.db.tables.AnswerTable
import cz.uuk.hek.db.tables.CardTable
import cz.uuk.hek.db.tables.LessonTable
import cz.uuk.hek.db.tables.QuestionTable
import cz.uuk.hek.db.tables.UserLessonResultTable
import cz.uuk.hek.di.appModule
import cz.uuk.hek.dto.CreateQuestionRequest
import cz.uuk.hek.service.LessonService
import cz.uuk.hek.service.QuestionService
import cz.uuk.hek.service.UserLessonResultService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
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
        SchemaUtils.createMissingTablesAndColumns(LessonTable, CardTable, QuestionTable, AnswerTable, UserLessonResultTable)
    }
    seedIfEmpty()

    install(Koin) {
        modules(appModule)
    }
    install(ContentNegotiation) {
        json()
    }

    val lessonService: LessonService by inject()
    val questionService: QuestionService by inject()
    val userLessonResultService: UserLessonResultService by inject()

    routing {
        get("/lessons") {
            val userId = call.request.headers["X-User-Id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(lessonService.getLessons(userId))
        }
        get("/lessons/{id}") {
            val userId = call.request.headers["X-User-Id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val lesson = lessonService.getLessonById(id, userId)
                ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(lesson)
        }
        post("/lessons/{id}/complete") {
            val userId = call.request.headers["X-User-Id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest)
            val lessonId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest)
            val progress = call.request.headers["X-Progress"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest)
            userLessonResultService.complete(userId, lessonId, progress)
            call.respond(HttpStatusCode.OK)
        }
        post("/questions") {
            val request = call.receive<CreateQuestionRequest>()
            val question = questionService.createQuestion(request)
            call.respond(HttpStatusCode.Created, question)
        }
    }
}
