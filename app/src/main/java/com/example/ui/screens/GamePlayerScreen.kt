package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GameQuestion
import com.example.data.model.GamesData
import com.example.data.model.LiteracyGameType
import com.example.ui.LiterasiViewModel
import com.example.ui.ScreenRoute
import com.example.ui.components.LiteracyTopAppBar
import com.example.ui.components.StarRatingRow
import com.example.util.SoundManager
import kotlin.random.Random

@Composable
fun GamePlayerScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val activeGameId by viewModel.activeGameId.collectAsState()
    val gameInfo = GamesData.gamesList.find { it.id == activeGameId } ?: GamesData.gamesList.first()

    // Game lifecycle states
    var isStarted by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var isFinished by remember { mutableStateOf(false) }
    var sfxEnabled by remember { mutableStateOf(SoundManager.sfxEnabled) }

    // Game scores & levels
    var score by remember { mutableIntStateOf(0) }
    var currentQuestionIdx by remember { mutableIntStateOf(0) }

    // Specific game state (e.g., Snakes & Ladders tile)
    var boardPosition by remember { mutableIntStateOf(1) }
    var diceValue by remember { mutableIntStateOf(1) }
    var isRollingDice by remember { mutableStateOf(false) }

    // Word Train rearranged words
    val currentSentenceWords = remember { mutableStateListOf<String>() }
    val selectedSentenceWords = remember { mutableStateListOf<String>() }

    // Airplane / Balloon active items
    var streakCount by remember { mutableIntStateOf(0) }

    // Trophy and stars calculation
    val starsEarned = when {
        score >= 80 -> 3
        score >= 50 -> 2
        score >= 20 -> 1
        else -> 0
    }
    val trophyEarned = starsEarned == 3

    fun restartGame() {
        SoundManager.playClick()
        isStarted = true
        isPaused = false
        isFinished = false
        score = 0
        currentQuestionIdx = 0
        boardPosition = 1
        streakCount = 0
        currentSentenceWords.clear()
        selectedSentenceWords.clear()
        if (gameInfo.gameType == LiteracyGameType.WORD_TRAIN) {
            val target = GamesData.wordTrainSentences.first().shuffled()
            currentSentenceWords.addAll(target)
        }
    }

    fun finishGame() {
        isFinished = true
        if (trophyEarned) {
            SoundManager.playTrophy()
        } else {
            SoundManager.playLevelUp()
        }
        viewModel.recordGameScore(gameInfo.id, score, starsEarned, trophyEarned)
    }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = gameInfo.title,
                onBackClick = { viewModel.navigateBack() }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .testTag("game_player_screen")
        ) {
            // Control Bar (Mulai, Pause/Lanjut, Ulangi, Kembali, Suara)
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                sfxEnabled = !sfxEnabled
                                viewModel.toggleSfx()
                            },
                            modifier = Modifier.testTag("btn_toggle_game_sfx")
                        ) {
                            Icon(
                                imageVector = if (sfxEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                                contentDescription = "Suara",
                                tint = if (sfxEnabled) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                        if (isStarted && !isFinished) {
                            IconButton(
                                onClick = {
                                    isPaused = !isPaused
                                    SoundManager.playClick()
                                },
                                modifier = Modifier.testTag("btn_pause_resume")
                            ) {
                                Icon(
                                    imageVector = if (isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                                    contentDescription = if (isPaused) "Lanjut" else "Jeda",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }

                        IconButton(
                            onClick = { restartGame() },
                            modifier = Modifier.testTag("btn_restart_game")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Replay,
                                contentDescription = "Ulangi",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Skor: $score",
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        StarRatingRow(starsEarned = starsEarned, starSize = 18)
                    }
                }
            }

            // Game Play Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                if (!isStarted) {
                    // Pre-game Welcome Screen
                    GameWelcomeCard(
                        gameInfo = gameInfo,
                        onStartGame = { restartGame() }
                    )
                } else if (isPaused) {
                    // Paused Card
                    GamePausedCard(onResume = { isPaused = false })
                } else if (isFinished) {
                    // Finished Celebration Card
                    GameFinishedCard(
                        gameInfo = gameInfo,
                        score = score,
                        stars = starsEarned,
                        trophyEarned = trophyEarned,
                        onPlayAgain = { restartGame() },
                        onBackToMenu = { viewModel.navigateTo(ScreenRoute.GAMES_MENU) }
                    )
                } else {
                    // Active Game Content Switcher
                    when (gameInfo.gameType) {
                        LiteracyGameType.SNAKES_LADDERS -> {
                            SnakesAndLaddersGameContent(
                                boardPosition = boardPosition,
                                diceValue = diceValue,
                                onRollDice = {
                                    SoundManager.playDiceRoll()
                                    diceValue = Random.nextInt(1, 7)
                                    val newPos = (boardPosition + diceValue).coerceAtMost(24)
                                    boardPosition = newPos
                                    score += 15
                                    if (boardPosition >= 24) {
                                        finishGame()
                                    }
                                },
                                onAnswerQuestion = { isCorrect ->
                                    if (isCorrect) {
                                        SoundManager.playCorrect()
                                        score += 25
                                        boardPosition = (boardPosition + 2).coerceAtMost(24)
                                    } else {
                                        SoundManager.playWrong()
                                        score = (score - 5).coerceAtLeast(0)
                                    }
                                    if (boardPosition >= 24) finishGame()
                                }
                            )
                        }
                        LiteracyGameType.WORD_TRAIN -> {
                            WordTrainGameContent(
                                availableWords = currentSentenceWords,
                                selectedWords = selectedSentenceWords,
                                onWordSelected = { word ->
                                    SoundManager.playClick()
                                    currentSentenceWords.remove(word)
                                    selectedSentenceWords.add(word)
                                },
                                onResetWords = {
                                    val target = GamesData.wordTrainSentences.first().shuffled()
                                    currentSentenceWords.clear()
                                    selectedSentenceWords.clear()
                                    currentSentenceWords.addAll(target)
                                },
                                onCheckSentence = {
                                    val correctSentence = GamesData.wordTrainSentences.first().joinToString(" ")
                                    val currentSentence = selectedSentenceWords.joinToString(" ")
                                    if (currentSentence == correctSentence) {
                                        SoundManager.playCorrect()
                                        score += 50
                                        finishGame()
                                    } else {
                                        SoundManager.playWrong()
                                    }
                                }
                            )
                        }
                        else -> {
                            // General Adventure Interactive Game (Pesawat, Roket, Detektif, Balon, dsb.)
                            StandardLiteracyGameContent(
                                gameType = gameInfo.gameType,
                                currentQuestionIdx = currentQuestionIdx,
                                onAnswer = { isCorrect ->
                                    if (isCorrect) {
                                        SoundManager.playCorrect()
                                        score += 25
                                        streakCount++
                                        if (streakCount >= 4) {
                                            finishGame()
                                        } else {
                                            currentQuestionIdx = (currentQuestionIdx + 1) % 4
                                        }
                                    } else {
                                        SoundManager.playWrong()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameWelcomeCard(
    gameInfo: com.example.data.model.LiteracyGameInfo,
    onStartGame: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(gameInfo.themeColorHex).copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gameInfo.iconEmoji, fontSize = 44.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = gameInfo.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = gameInfo.subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color(gameInfo.themeColorHex)
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = gameInfo.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onStartGame,
                colors = ButtonDefaults.buttonColors(containerColor = Color(gameInfo.themeColorHex)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(52.dp)
                    .testTag("btn_start_game_play")
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Mulai")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Mulai Petualangan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun GamePausedCard(onResume: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "⏸️", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Game Dijeda",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onResume,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.testTag("btn_resume_game")
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Lanjut")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lanjutkan Permainan")
            }
        }
    }
}

@Composable
fun GameFinishedCard(
    gameInfo: com.example.data.model.LiteracyGameInfo,
    score: Int,
    stars: Int,
    trophyEarned: Boolean,
    onPlayAgain: () -> Unit,
    onBackToMenu: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = if (trophyEarned) "🏆 🌟 🎉" else "⭐ 👏 ✨", fontSize = 42.sp)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (trophyEarned) "Juara Hebat!" else "Permainan Selesai!",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = "Skor Akhir: $score Poin",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF2E7D32)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            StarRatingRow(starsEarned = stars, starSize = 34)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = when (stars) {
                    3 -> "⭐⭐⭐ Hebat! Kamu menaklukkan game ini!"
                    2 -> "⭐⭐ Cukup baik! Tingkatkan lagi!"
                    1 -> "⭐ Mulai belajar! Teruslah mencoba!"
                    else -> "Yuk latihan lagi!"
                },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                textAlign = TextAlign.Center
            )

            if (trophyEarned) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = Color(0xFFFFF8E1),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "🏆 Bonus Piala Berhasil Diraih!",
                        color = Color(0xFFE65100),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = onBackToMenu,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pilih Game Lain")
                }
                Button(
                    onClick = onPlayAgain,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Main Lagi")
                }
            }
        }
    }
}

// 1. Snakes and Ladders Game Content
@Composable
fun SnakesAndLaddersGameContent(
    boardPosition: Int,
    diceValue: Int,
    onRollDice: () -> Unit,
    onAnswerQuestion: (Boolean) -> Unit
) {
    val currentQuestion = GamesData.snakesLaddersQuestions.first()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Posisi: Kotak $boardPosition / 24",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Dadu:", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer
                            ) {
                                Text(
                                    text = "🎲 $diceValue",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Simplified board grid representation
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items((1..24).toList()) { tile ->
                            val isPlayerHere = tile == boardPosition
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isPlayerHere) Color(0xFF1E88E5) else Color(0xFFECEFF1)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isPlayerHere) "🏃" else "$tile",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isPlayerHere) Color.White else Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }
        }

        // Dice Roll Action
        item {
            Button(
                onClick = onRollDice,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_roll_dice")
            ) {
                Icon(imageVector = Icons.Default.Casino, contentDescription = "Dadu")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Lempar Dadu untuk Melangkah!", fontWeight = FontWeight.Bold)
            }
        }

        // Literacy Challenge on tile
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Tantangan Kotak Tangga Bonus 🪜",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = currentQuestion.prompt, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(10.dp))

                    currentQuestion.options.forEachIndexed { idx, opt ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onAnswerQuestion(idx == currentQuestion.correctIndex) }
                        ) {
                            Text(
                                text = opt,
                                modifier = Modifier.padding(10.dp),
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// 2. Word Train Game Content (Drag/tap wagons to assemble sentence)
@Composable
fun WordTrainGameContent(
    availableWords: List<String>,
    selectedWords: List<String>,
    onWordSelected: (String) -> Unit,
    onResetWords: () -> Unit,
    onCheckSentence: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "🚂 Rangkaian Gerbong Kereta Kalimat:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))

                if (selectedWords.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ketuk kata di bawah untuk merangkai gerbong...",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                } else {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(selectedWords) { word ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = "🚃 $word",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Available words pool
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Kata yang Tersedia (Ketuk untuk Memilih):",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(availableWords) { word ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable { onWordSelected(word) }
                        ) {
                            Text(
                                text = word,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
                onClick = onResetWords,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Ulangi Susunan")
            }
            Button(
                onClick = onCheckSentence,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Jalankan Kereta 🚀", fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 3. Standard Literacy Adventure Content for other games
@Composable
fun StandardLiteracyGameContent(
    gameType: LiteracyGameType,
    currentQuestionIdx: Int,
    onAnswer: (Boolean) -> Unit
) {
    val questions = when (gameType) {
        LiteracyGameType.AIRPLANE_FLY -> GamesData.airplaneQuestions
        LiteracyGameType.ROCKET_PLANET -> GamesData.rocketQuestions
        LiteracyGameType.TREASURE_HUNT -> GamesData.treasureClues
        else -> GamesData.snakesLaddersQuestions
    }

    val currentQ = questions.getOrNull(currentQuestionIdx) ?: questions.first()

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Tantangan ${currentQuestionIdx + 1} / 4",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = when (gameType) {
                            LiteracyGameType.AIRPLANE_FLY -> "✈️ Awan Pilihan"
                            LiteracyGameType.ROCKET_PLANET -> "🚀 Planet Kata"
                            LiteracyGameType.TREASURE_HUNT -> "🗺️ Peta Rahasia"
                            LiteracyGameType.MAIN_IDEA_DETECTIVE -> "🔍 Kaca Pembesar"
                            LiteracyGameType.WORD_BALLOONS -> "🎈 Balon Melayang"
                            else -> "⛵ Pulau Pengetahuan"
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = currentQ.prompt,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                currentQ.options.forEachIndexed { idx, opt ->
                    val isCorrect = idx == currentQ.correctIndex
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable { onAnswer(isCorrect) }
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "✨", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = opt,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            if (currentQ.hint.isNotEmpty()) {
                Surface(
                    color = Color(0xFFFFF9C4),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💡 Petunjuk: ${currentQ.hint}",
                        fontSize = 11.sp,
                        color = Color(0xFF5D4037),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
