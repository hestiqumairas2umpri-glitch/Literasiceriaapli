package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_profile")
data class StudentProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Siswa Juara",
    val studentClass: String = "Kelas 6 SD",
    val avatarId: Int = 0,
    val role: String = "SISWA", // SISWA or GURU
    val totalReadingMinutes: Int = 0
)

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val storyId: Int,
    val isCompleted: Boolean = false,
    val bestScore: Int = 0,
    val starsEarned: Int = 0,
    val timeSpentSeconds: Int = 0,
    val lastCompletedAt: Long = 0L
)

@Entity(tableName = "game_progress")
data class GameProgressEntity(
    @PrimaryKey val gameId: Int,
    val isCompleted: Boolean = false,
    val starsEarned: Int = 0,
    val highestScore: Int = 0,
    val trophyEarned: Boolean = false
)

@Entity(tableName = "quiz_history")
data class QuizHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val storyId: Int,
    val storyTitle: String,
    val score: Int,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val timeTakenSeconds: Int,
    val masteryCategory: String, // Perlu Latihan, Mulai Berkembang, Berkembang Baik, Sangat Baik
    val timestamp: Long = System.currentTimeMillis()
)
