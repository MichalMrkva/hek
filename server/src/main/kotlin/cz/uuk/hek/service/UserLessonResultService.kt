package cz.uuk.hek.service

import cz.uuk.hek.repository.UserLessonResultRepository

class UserLessonResultService(private val userLessonResultRepository: UserLessonResultRepository) {

    fun complete(userId: Int, lessonId: Int, result: Int) =
        userLessonResultRepository.upsert(userId, lessonId, result)

    fun getProgress(userId: Int, lessonId: Int): Int? =
        userLessonResultRepository.getResult(userId, lessonId)
}
