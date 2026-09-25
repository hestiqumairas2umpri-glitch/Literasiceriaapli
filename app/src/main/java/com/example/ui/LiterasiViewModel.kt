package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.GameProgressEntity
import com.example.data.database.LiterasiDatabase
import com.example.data.database.QuizHistoryEntity
import com.example.data.database.ReadingProgressEntity
import com.example.data.database.StudentProfileEntity
import com.example.data.model.LiteracySong
import com.example.data.model.ReadingStory
import com.example.data.model.SongsData
import com.example.data.model.StoriesProvider
import com.example.data.model.TrophiesAndBadgesData
import com.example.data.repository.LiterasiRepository
import com.example.util.SoundManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class ScreenRoute {
    HOME,
    LEARNING_OBJECTIVES,
    STORY_LIST,
    STORY_READER,
    QUIZ,
    QUIZ_RESULT,
    GAMES_MENU,
    GAME_PLAYER,
    MOTIVATION,
    VIDEOS,
    PROGRESS_DASHBOARD,
    TROPHIES,
    TEACHER_REPORT,
    SONGS,
    PROFILE,
    SETTINGS
}

data class QuizSessionState(
    val storyId: Int,
    val storyTitle: String,
    val currentQuestionIndex: Int = 0,
    val userAnswers: MutableMap<Int, Int> = mutableMapOf(),
    val showFeedbackDialog: Boolean = false,
    val isCurrentAnswerCorrect: Boolean = false,
    val currentExplanation: String = "",
    val score: Int = 0,
    val correctCount: Int = 0,
    val wrongCount: Int = 0,
    val startTimeMs: Long = System.currentTimeMillis(),
    val timeTakenSeconds: Int = 0
)

class LiterasiViewModel(application: Application) : AndroidViewModel(application) {
    private val database = LiterasiDatabase.getInstance(application)
    private val repository = LiterasiRepository(database.literasiDao())

    val profileState: StateFlow<StudentProfileEntity?> = repository.profileFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentProfileEntity())

    val readingProgressState: StateFlow<List<ReadingProgressEntity>> = repository.readingProgressFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val gameProgressState: StateFlow<List<GameProgressEntity>> = repository.gameProgressFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizHistoryState: StateFlow<List<QuizHistoryEntity>> = repository.quizHistoryFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Navigation state
    private val _currentRoute = MutableStateFlow(ScreenRoute.HOME)
    val currentRoute: StateFlow<ScreenRoute> = _currentRoute.asStateFlow()

    private val navBackStack = mutableListOf<ScreenRoute>()

    // Story Reader state
    private val _selectedStory = MutableStateFlow<ReadingStory?>(null)
    val selectedStory: StateFlow<ReadingStory?> = _selectedStory.asStateFlow()

    // Text size multiplier (1.0f = Normal, 1.25f = Sedang, 1.5f = Besar)
    private val _fontScale = MutableStateFlow(1.0f)
    val fontScale: StateFlow<Float> = _fontScale.asStateFlow()

    // High Contrast Mode
    private val _highContrastMode = MutableStateFlow(false)
    val highContrastMode: StateFlow<Boolean> = _highContrastMode.asStateFlow()

    // Quiz Session State
    private val _quizState = MutableStateFlow<QuizSessionState?>(null)
    val quizState: StateFlow<QuizSessionState?> = _quizState.asStateFlow()

    // Active Game ID
    private val _activeGameId = MutableStateFlow(1)
    val activeGameId: StateFlow<Int> = _activeGameId.asStateFlow()

    // Songs Player State
    private val _currentSongIndex = MutableStateFlow(0)
    val currentSongIndex: StateFlow<Int> = _currentSongIndex.asStateFlow()

    private val _isSongPlaying = MutableStateFlow(false)
    val isSongPlaying: StateFlow<Boolean> = _isSongPlaying.asStateFlow()

    private val _currentLyricLineIndex = MutableStateFlow(0)
    val currentLyricLineIndex: StateFlow<Int> = _currentLyricLineIndex.asStateFlow()

    private var songJob: Job? = null

    init {
        // Ensure default profile exists
        viewModelScope.launch {
            repository.getProfile()
        }
    }

    fun navigateTo(route: ScreenRoute) {
        if (_currentRoute.value != route) {
            navBackStack.add(_currentRoute.value)
            _currentRoute.value = route
            SoundManager.playClick()
        }
    }

    fun navigateBack(): Boolean {
        if (navBackStack.isNotEmpty()) {
            val previous = navBackStack.removeAt(navBackStack.size - 1)
            _currentRoute.value = previous
            SoundManager.playClick()
            return true
        }
        if (_currentRoute.value != ScreenRoute.HOME) {
            _currentRoute.value = ScreenRoute.HOME
            SoundManager.playClick()
            return true
        }
        return false
    }

    fun openStory(storyId: Int) {
        val story = StoriesProvider.getStoryById(storyId)
        if (story != null) {
            _selectedStory.value = story
            navigateTo(ScreenRoute.STORY_READER)
        }
    }

    fun setFontScale(scale: Float) {
        _fontScale.value = scale
        SoundManager.playClick()
    }

    fun toggleHighContrast() {
        _highContrastMode.value = !_highContrastMode.value
        SoundManager.playClick()
    }

    fun toggleSfx() {
        SoundManager.sfxEnabled = !SoundManager.sfxEnabled
        if (SoundManager.sfxEnabled) SoundManager.playClick()
    }

    fun toggleMusic() {
        SoundManager.musicEnabled = !SoundManager.musicEnabled
        if (!SoundManager.musicEnabled) {
            stopSongPlayback()
        } else {
            SoundManager.playClick()
        }
    }

    fun updateProfile(name: String, studentClass: String, avatarId: Int, role: String) {
        viewModelScope.launch {
            repository.updateProfile(name, studentClass, avatarId, role)
            SoundManager.playLevelUp()
        }
    }

    // --- QUIZ MANAGEMENT ---
    fun startQuizForStory(storyId: Int) {
        val story = StoriesProvider.getStoryById(storyId) ?: return
        _quizState.value = QuizSessionState(
            storyId = storyId,
            storyTitle = story.title,
            currentQuestionIndex = 0,
            startTimeMs = System.currentTimeMillis()
        )
        navigateTo(ScreenRoute.QUIZ)
    }

    fun submitAnswer(questionIndex: Int, selectedOptionIndex: Int) {
        val state = _quizState.value ?: return
        val questions = StoriesProvider.getQuestionsForStory(state.storyId)
        if (questionIndex !in questions.indices) return

        val question = questions[questionIndex]
        val isCorrect = selectedOptionIndex == question.correctIndex

        val newAnswers = HashMap(state.userAnswers)
        newAnswers[questionIndex] = selectedOptionIndex

        val newCorrectCount = if (isCorrect) state.correctCount + 1 else state.correctCount
        val newWrongCount = if (!isCorrect) state.wrongCount + 1 else state.wrongCount

        if (isCorrect) {
            SoundManager.playCorrect()
        } else {
            SoundManager.playWrong()
        }

        _quizState.value = state.copy(
            userAnswers = newAnswers,
            showFeedbackDialog = true,
            isCurrentAnswerCorrect = isCorrect,
            currentExplanation = question.explanation,
            correctCount = newCorrectCount,
            wrongCount = newWrongCount
        )
    }

    fun dismissFeedbackAndNext() {
        val state = _quizState.value ?: return
        val questions = StoriesProvider.getQuestionsForStory(state.storyId)
        val nextIndex = state.currentQuestionIndex + 1

        if (nextIndex < questions.size) {
            _quizState.value = state.copy(
                currentQuestionIndex = nextIndex,
                showFeedbackDialog = false
            )
        } else {
            // Quiz finished! Calculate score & persist
            val timeTakenSec = ((System.currentTimeMillis() - state.startTimeMs) / 1000).toInt().coerceAtLeast(10)
            val totalQuestions = questions.size
            val calculatedScore = if (totalQuestions > 0) ((state.correctCount.toFloat() / totalQuestions) * 100).toInt() else 0

            _quizState.value = state.copy(
                showFeedbackDialog = false,
                score = calculatedScore,
                timeTakenSeconds = timeTakenSec
            )

            viewModelScope.launch {
                repository.saveReadingAndQuizResult(
                    storyId = state.storyId,
                    storyTitle = state.storyTitle,
                    score = calculatedScore,
                    correctCount = state.correctCount,
                    wrongCount = state.wrongCount,
                    timeSeconds = timeTakenSec
                )
                if (calculatedScore >= 80) {
                    SoundManager.playTrophy()
                } else {
                    SoundManager.playStar()
                }
            }
            navigateTo(ScreenRoute.QUIZ_RESULT)
        }
    }

    fun retryQuiz() {
        val state = _quizState.value ?: return
        startQuizForStory(state.storyId)
    }

    // --- GAME MANAGEMENT ---
    fun selectGame(gameId: Int) {
        _activeGameId.value = gameId
        navigateTo(ScreenRoute.GAME_PLAYER)
    }

    fun recordGameScore(gameId: Int, score: Int, stars: Int, trophyEarned: Boolean) {
        viewModelScope.launch {
            repository.saveGameProgress(gameId, score, stars, trophyEarned)
            if (trophyEarned) {
                SoundManager.playTrophy()
            } else {
                SoundManager.playStar()
            }
        }
    }

    // --- SONGS MANAGEMENT ---
    fun selectSong(index: Int) {
        _currentSongIndex.value = index
        stopSongPlayback()
    }

    fun togglePlaySong() {
        if (_isSongPlaying.value) {
            stopSongPlayback()
        } else {
            startSongPlayback()
        }
    }

    private fun startSongPlayback() {
        val song = SongsData.songs.getOrNull(_currentSongIndex.value) ?: return
        _isSongPlaying.value = true
        _currentLyricLineIndex.value = 0

        songJob?.cancel()
        songJob = viewModelScope.launch {
            // Play synthesized melodies & lyrics sync
            SoundManager.playMelody(song.melodyNotes)
            val startTime = System.currentTimeMillis()
            while (isActive && _isSongPlaying.value) {
                val elapsed = System.currentTimeMillis() - startTime
                val currentLineIdx = song.lyricsLines.indexOfLast { elapsed >= it.startTimeMs }
                if (currentLineIdx >= 0) {
                    _currentLyricLineIndex.value = currentLineIdx
                }
                val totalDuration = song.lyricsLines.lastOrNull()?.let { it.startTimeMs + it.durationMs } ?: 30000L
                if (elapsed > totalDuration) {
                    _isSongPlaying.value = false
                    break
                }
                delay(200)
            }
        }
    }

    fun stopSongPlayback() {
        _isSongPlaying.value = false
        songJob?.cancel()
        songJob = null
        SoundManager.stopMusic()
    }

    // Check if story is unlocked:
    // Story 1 is always unlocked. Story N is unlocked if Story N-1 has been completed
    fun isStoryUnlocked(storyId: Int, completedList: List<ReadingProgressEntity>): Boolean {
        if (storyId <= 1) return true
        val prevCompleted = completedList.any { it.storyId == storyId - 1 && it.isCompleted }
        return prevCompleted
    }

    override fun onCleared() {
        super.onCleared()
        stopSongPlayback()
    }
}
