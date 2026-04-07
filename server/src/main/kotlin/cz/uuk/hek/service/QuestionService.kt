package cz.uuk.hek.service

import cz.uuk.hek.dto.CreateQuestionRequest
import cz.uuk.hek.dto.QuestionDto
import cz.uuk.hek.repository.QuestionRepository

class QuestionService(private val questionRepository: QuestionRepository) {

    fun createQuestion(request: CreateQuestionRequest): QuestionDto =
        questionRepository.create(request)
}
