package cz.uuk.hek.service

import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import cz.uuk.hek.repository.LessonRepository

class LessonService(private val lessonRepository: LessonRepository) {

    fun getLessons(userId: Int): List<LessonSummaryDto> = lessonRepository.getAll(userId)

    fun getLessonById(id: Int, userId: Int): LessonDto? = lessonRepository.getById(id, userId)
}
