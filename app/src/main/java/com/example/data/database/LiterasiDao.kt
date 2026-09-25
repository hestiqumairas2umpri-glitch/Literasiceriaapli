package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LiterasiDao {
    @Query("SELECT * FROM student_profile WHERE id = 1 LIMIT 1")
    fun getProfileFlow(): Flow<StudentProfileEntity?>

    @Query("SELECT * FROM student_profile WHERE id = 1 LIMIT 1")
    suspend fun getProfile(): StudentProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: StudentProfileEntity)

    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgressFlow(): Flow<List<ReadingProgressEntity>>

    @Query("SELECT * FROM reading_progress WHERE storyId = :storyId LIMIT 1")
    suspend fun getReadingProgress(storyId: Int): ReadingProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgressEntity)

    @Query("SELECT * FROM game_progress")
    fun getAllGameProgressFlow(): Flow<List<GameProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGameProgress(progress: GameProgressEntity)

    @Query("SELECT * FROM quiz_history ORDER BY timestamp DESC")
    fun getAllQuizHistoryFlow(): Flow<List<QuizHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizHistory(history: QuizHistoryEntity)

    @Query("UPDATE student_profile SET totalReadingMinutes = totalReadingMinutes + :minutes WHERE id = 1")
    suspend fun addReadingMinutes(minutes: Int)
}
