package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.LiterasiViewModel
import com.example.ui.ScreenRoute
import com.example.ui.components.LiteracyTopAppBar
import com.example.ui.components.StarRatingRow

@Composable
fun QuizResultScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val quizState by viewModel.quizState.collectAsState()

    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    if (quizState == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Tidak ada hasil kuis yang tersedia.")
        }
        return
    }

    val state = quizState!!
    val score = state.score
    val correctCount = state.correctCount
    val wrongCount = state.wrongCount
    val totalCount = correctCount + wrongCount
    val percentage = if (totalCount > 0) ((correctCount.toFloat() / totalCount) * 100).toInt() else 0

    val category = when {
        score >= 85 -> "Sangat Baik"
        score >= 70 -> "Berkembang Baik"
        score >= 50 -> "Mulai Berkembang"
        else -> "Perlu Latihan"
    }

    val categoryColor = when (category) {
        "Sangat Baik" -> Color(0xFF2E7D32)
        "Berkembang Baik" -> Color(0xFF1E88E5)
        "Mulai Berkembang" -> Color(0xFFFFA000)
        else -> Color(0xFFE53935)
    }

    val stars = when {
        score >= 90 -> 3
        score >= 70 -> 2
        score >= 50 -> 1
        else -> 0
    }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Hasil Literasi",
                onHomeClick = { viewModel.navigateTo(ScreenRoute.HOME) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .testTag("quiz_result_screen"),
            contentPadding = PaddingValues(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Celebration Banner
            item {
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp).let {
                        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (score >= 80) "🎉 🌟 🏆" else "💪 📚 ✨",
                            fontSize = 36.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (score >= 80) "Selamat! Kamu Luar Biasa!" else "Bagus! Kamu Sudah Berusaha!",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                        )

                        Text(
                            text = "Bacaan: ${state.storyTitle}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Big Score Ring
                        Box(
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .background(categoryColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$score",
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        fontWeight = FontWeight.Black,
                                        fontSize = 38.sp,
                                        color = categoryColor
                                    )
                                )
                                Text(
                                    text = "Skor Akhir",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = categoryColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        StarRatingRow(starsEarned = stars, starSize = 32)

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            color = categoryColor.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Kategori: $category",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = categoryColor,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }

            // Stat Breakdown Cards
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Rincian Pengerjaan Soal",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatItem(
                                label = "Jawaban Benar",
                                value = "$correctCount Soal",
                                icon = Icons.Default.Check,
                                color = Color(0xFF2E7D32)
                            )
                            StatItem(
                                label = "Jawaban Salah",
                                value = "$wrongCount Soal",
                                icon = Icons.Default.Close,
                                color = Color(0xFFD32F2F)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatItem(
                                label = "Persentase",
                                value = "$percentage%",
                                icon = Icons.Default.Celebration,
                                color = MaterialTheme.colorScheme.primary
                            )
                            val minutes = state.timeTakenSeconds / 60
                            val seconds = state.timeTakenSeconds % 60
                            StatItem(
                                label = "Waktu Kerja",
                                value = String.format("%02d:%02d", minutes, seconds),
                                icon = Icons.Default.MenuBook,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }

            // Navigation Actions (Retry, Play Game, Next Story)
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.selectGame(state.storyId)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("btn_proceed_to_game")
                    ) {
                        Icon(imageVector = Icons.Default.Games, contentDescription = "Game")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Mainkan Game Literasi", fontWeight = FontWeight.Bold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.retryQuiz() },
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("btn_retry_quiz")
                        ) {
                            Icon(imageVector = Icons.Default.Replay, contentDescription = "Ulangi")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Ulangi Soal")
                        }

                        Button(
                            onClick = { viewModel.navigateTo(ScreenRoute.STORY_LIST) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("btn_all_stories")
                        ) {
                            Icon(imageVector = Icons.Default.MenuBook, contentDescription = "Daftar")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Cerita Lain")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
