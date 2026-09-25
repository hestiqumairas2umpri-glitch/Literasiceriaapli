package com.example.data.repository

import com.example.data.database.GameProgressEntity
import com.example.data.database.LiterasiDao
import com.example.data.database.QuizHistoryEntity
import com.example.data.database.ReadingProgressEntity
import com.example.data.database.StudentProfileEntity
import kotlinx.coroutines.flow.Flow

class LiterasiRepository(private val dao: LiterasiDao) {
    val profileFlow: Flow<StudentProfileEntity?> = dao.getProfileFlow()
    val readingProgressFlow: Flow<List<ReadingProgressEntity>> = dao.getAllReadingProgressFlow()
    val gameProgressFlow: Flow<List<GameProgressEntity>> = dao.getAllGameProgressFlow()
    val quizHistoryFlow: Flow<List<QuizHistoryEntity>> = dao.getAllQuizHistoryFlow()

    suspend fun getProfile(): StudentProfileEntity {
        return dao.getProfile() ?: StudentProfileEntity().also { dao.saveProfile(it) }
    }

    suspend fun updateProfile(name: String, studentClass: String, avatarId: Int, role: String) {
        val current = dao.getProfile() ?: StudentProfileEntity()
        dao.saveProfile(
            current.copy(
                name = name,
                studentClass = studentClass,
                avatarId = avatarId,
                role = role
            )
        )
    }

    suspend fun saveReadingAndQuizResult(
        storyId: Int,
        storyTitle: String,
        score: Int,
        correctCount: Int,
        wrongCount: Int,
        timeSeconds: Int
    ) {
        val stars = when {
            score >= 90 -> 3
            score >= 70 -> 2
            score >= 50 -> 1
            else -> 0
        }

        val category = when {
            score >= 85 -> "Sangat Baik"
            score >= 70 -> "Berkembang Baik"
            score >= 50 -> "Mulai Berkembang"
            else -> "Perlu Latihan"
        }

        val existing = dao.getReadingProgress(storyId)
        val bestScore = maxOf(score, existing?.bestScore ?: 0)
        val bestStars = maxOf(stars, existing?.starsEarned ?: 0)

        dao.saveReadingProgress(
            ReadingProgressEntity(
                storyId = storyId,
                isCompleted = true,
                bestScore = bestScore,
                starsEarned = bestStars,
                timeSpentSeconds = (existing?.timeSpentSeconds ?: 0) + timeSeconds,
                lastCompletedAt = System.currentTimeMillis()
            )
        )

        dao.insertQuizHistory(
            QuizHistoryEntity(
                storyId = storyId,
                storyTitle = storyTitle,
                score = score,
                correctAnswers = correctCount,
                wrongAnswers = wrongCount,
                timeTakenSeconds = timeSeconds,
                masteryCategory = category,
                timestamp = System.currentTimeMillis()
            )
        )

        dao.addReadingMinutes(maxOf(1, timeSeconds / 60))
    }

    suspend fun saveGameProgress(gameId: Int, score: Int, stars: Int, trophy: Boolean) {
        dao.saveGameProgress(
            GameProgressEntity(
                gameId = gameId,
                isCompleted = true,
                starsEarned = stars,
                highestScore = score,
                trophyEarned = trophy
            )
        )
    }
}
