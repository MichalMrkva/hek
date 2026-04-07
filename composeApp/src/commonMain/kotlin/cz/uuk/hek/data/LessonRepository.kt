package cz.uuk.hek.data

import cz.uuk.hek.SERVER_PORT
import cz.uuk.hek.data.mapper.toDomain
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary
import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object LessonRepository {

    private val client = HttpClient {
        install(ContentNegotiation) { json() }
    }

    private val baseUrl = "http://localhost:$SERVER_PORT"

    suspend fun getLessons(): List<LessonSummary> =
        client.get("$baseUrl/lessons").body<List<LessonSummaryDto>>().map { it.toDomain() }

    suspend fun getLesson(id: Int): Lesson =
        client.get("$baseUrl/lessons/$id").body<LessonDto>().toDomain()
}
