package cz.uuk.hek.service

import cz.uuk.hek.dto.LessonDto
import cz.uuk.hek.dto.LessonSummaryDto
import cz.uuk.hek.repository.LessonRepository

class LessonService(private val lessonRepository: LessonRepository) {

    fun getLessons(): List<LessonSummaryDto> = lessonRepository.getAll()

    fun getLessonById(id: Int): LessonDto? = lessonRepository.getById(id)
}
