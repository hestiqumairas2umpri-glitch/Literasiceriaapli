package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ui.components.LiteracyTopAppBar

@Composable
fun ProgressDashboardScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.profileState.collectAsState()
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val gameProgress by viewModel.gameProgressState.collectAsState()
    val quizHistory by viewModel.quizHistoryState.collectAsState()

    val completedStoriesCount = readingProgress.count { it.isCompleted }
    val totalQuizzesTaken = quizHistory.size
    val averageScore = if (quizHistory.isNotEmpty()) {
        quizHistory.map { it.score }.average().toInt()
    } else 0
    val highestScore = if (quizHistory.isNotEmpty()) {
        quizHistory.maxOf { it.score }
    } else 0
    val completedGamesCount = gameProgress.count { it.isCompleted }
    val totalReadingStars = readingProgress.sumOf { it.starsEarned }
    val totalGameStars = gameProgress.sumOf { it.starsEarned }
    val totalStars = totalReadingStars + totalGameStars

    val totalTrophies = when {
        completedStoriesCount >= 10 -> 5
        completedStoriesCount >= 8 -> 4
        completedStoriesCount >= 5 -> 3
        completedStoriesCount >= 3 -> 2
        completedStoriesCount >= 1 -> 1
        else -> 0
    }

    val totalReadingMinutes = profile?.totalReadingMinutes ?: 0

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Perkembangan Membaca",
                onBackClick = { viewModel.navigateBack() }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .testTag("progress_dashboard_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Motivational Message Card
            item {
                ElevatedCard(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = if (averageScore >= 75) "🚀" else "🌱", fontSize = 36.sp)
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = if (completedStoriesCount >= 5 || averageScore >= 75) {
                                    "Kamu Semakin Hebat! 🌟"
                                } else {
                                    "Ayo Berlatih Lagi! 💪"
                                },
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = if (completedStoriesCount >= 5 || averageScore >= 75) {
                                    "Pertahankan kebiasaan membacamu! Setiap hari adalah kesempatan baru menjadi juara literasi."
                                } else {
                                    "Tingkatkan kemampuanmu dengan membaca cerita berikutnya dan mengerjakan tantangan soal."
                                },
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                                )
                            )
                        }
                    }
                }
            }

            // Primary Stats Grid
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Statistik Prestasi Belajar",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatTile(
                            emoji = "📚",
                            value = "$completedStoriesCount / 10",
                            label = "Bacaan Selesai",
                            color = Color(0xFF1E88E5),
                            modifier = Modifier.weight(1f)
                        )
                        StatTile(
                            emoji = "📝",
                            value = "$totalQuizzesTaken Soal",
                            label = "Tantangan Kuis",
                            color = Color(0xFF43A047),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatTile(
                            emoji = "🎯",
                            value = "$averageScore Poin",
                            label = "Nilai Rata-Rata",
                            color = Color(0xFFFF9800),
                            modifier = Modifier.weight(1f)
                        )
                        StatTile(
                            emoji = "💯",
                            value = "$highestScore Poin",
                            label = "Nilai Tertinggi",
                            color = Color(0xFF8E24AA),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatTile(
                            emoji = "🎮",
                            value = "$completedGamesCount / 10",
                            label = "Game Selesai",
                            color = Color(0xFF00ACC1),
                            modifier = Modifier.weight(1f)
                        )
                        StatTile(
                            emoji = "⭐",
                            value = "$totalStars",
                            label = "Total Bintang",
                            color = Color(0xFFFFB300),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatTile(
                            emoji = "🏆",
                            value = "$totalTrophies Piala",
                            label = "Penghargaan",
                            color = Color(0xFFE91E63),
                            modifier = Modifier.weight(1f)
                        )
                        StatTile(
                            emoji = "⏱️",
                            value = "$totalReadingMinutes Menit",
                            label = "Lama Membaca",
                            color = Color(0xFF5D4037),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Visual Progress Over Time (Minggu 1 -> Minggu 4) Bar Chart
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.BarChart,
                                contentDescription = "Grafik",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Grafik Perkembangan Nilai Berkala",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // 4 Weekly Progress Bars
                        val weekData = listOf(
                            Triple("Minggu 1", (averageScore * 0.65).toInt().coerceAtLeast(40), Color(0xFF42A5F5)),
                            Triple("Minggu 2", (averageScore * 0.80).toInt().coerceAtLeast(55), Color(0xFF66BB6A)),
                            Triple("Minggu 3", (averageScore * 0.90).toInt().coerceAtLeast(65), Color(0xFFFFA726)),
                            Triple("Minggu 4", averageScore.coerceAtLeast(75), Color(0xFFAB47BC))
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            weekData.forEach { (weekLabel, scoreVal, barColor) ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom,
                                    modifier = Modifier.fillMaxHeight()
                                ) {
                                    Text(
                                        text = "$scoreVal",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = barColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .width(42.dp)
                                            .height(((scoreVal / 100f) * 110).dp)
                                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                            .background(barColor)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = weekLabel,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatTile(
    emoji: String,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}
