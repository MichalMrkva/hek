package cz.uuk.hek.data

import cz.uuk.hek.SERVER_PORT
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

    suspend fun getLessons(): List<LessonSummaryDto> =
        client.get("$baseUrl/lessons").body()

    suspend fun getLesson(id: Int): LessonDto =
        client.get("$baseUrl/lessons/$id").body()
}
