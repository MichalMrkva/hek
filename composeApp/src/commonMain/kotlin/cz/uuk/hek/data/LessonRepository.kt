package cz.uuk.hek.data

import cz.uuk.hek.SERVER_PORT
import cz.uuk.hek.data.mapper.toDomain
import cz.uuk.hek.domain.model.Lesson
import cz.uuk.hek.domain.model.LessonSummary
import cz.uuk.hek.domain.model.UserMetadata
import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json

class LessonRepository(userMetadata: UserMetadata) {

    private val client = HttpClient {
        install(ContentNegotiation) { json() }
        defaultRequest {
            header("X-User-Id", userMetadata.userId)
        }
    }

    private val baseUrl = "http://$localServerHost:$SERVER_PORT"

    suspend fun getLessons(): List<LessonSummary> =
        client.get("$baseUrl/lessons").body<List<LessonSummaryDto>>().map { it.toDomain() }

    suspend fun getLesson(id: Int): Lesson =
        client.get("$baseUrl/lessons/$id").body<LessonDto>().toDomain()

    suspend fun completeLesson(lessonId: Int, progress: Int) {
        client.post("$baseUrl/lessons/$lessonId/complete") {
            header("X-Progress", progress)
        }
    }
}
