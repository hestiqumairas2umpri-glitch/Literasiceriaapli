package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.MotivationsData
import com.example.ui.LiterasiViewModel
import com.example.ui.ScreenRoute
import com.example.ui.components.QuickStatBadge

@Composable
fun HomeScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.profileState.collectAsState()
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val gameProgress by viewModel.gameProgressState.collectAsState()

    val completedStoriesCount = readingProgress.count { it.isCompleted }
    val totalReadingStars = readingProgress.sumOf { it.starsEarned }
    val totalGameStars = gameProgress.sumOf { it.starsEarned }
    val totalStars = totalReadingStars + totalGameStars
    val completedGamesCount = gameProgress.count { it.isCompleted }

    // Count trophies
    val trophyCount = when {
        completedStoriesCount >= 10 -> 5
        completedStoriesCount >= 8 -> 4
        completedStoriesCount >= 5 -> 3
        completedStoriesCount >= 3 -> 2
        completedStoriesCount >= 1 -> 1
        else -> 0
    }

    val dailyQuote = MotivationsData.quotes.first()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("home_screen_content"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // 1. Top Header with User Greeting & Role
        item {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (profile?.avatarId) {
                                    1 -> "🦉"
                                    2 -> "🐱"
                                    3 -> "🚀"
                                    4 -> "🐰"
                                    5 -> "🤖"
                                    else -> "🦊"
                                },
                                fontSize = 26.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Halo, ${profile?.name ?: "Siswa Juara"}! 👋",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            )
                            Text(
                                text = "${profile?.studentClass ?: "Kelas 6 SD"} • Petualang Literasi",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }

                    Row {
                        IconButton(
                            onClick = { viewModel.navigateTo(ScreenRoute.PROFILE) },
                            modifier = Modifier.testTag("home_profile_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profil",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(
                            onClick = { viewModel.navigateTo(ScreenRoute.SETTINGS) },
                            modifier = Modifier.testTag("home_settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Pengaturan",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // 2. Hero Banner Card with Illustration
        item {
            ElevatedCard(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .testTag("home_hero_card")
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.img_literasi_hero),
                        contentDescription = "Petualangan Membaca Hero Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.85f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "KELAS 6 SD",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Petualangan Literasi",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Baca, Pahami, Bermain, dan Jadilah Juara! 🚀",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        )
                    }
                }
            }
        }

        // 3. Progress Snapshot Badges
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    text = "Progres Petualanganmu",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickStatBadge(
                        iconText = "📚",
                        countText = "$completedStoriesCount / 10",
                        labelText = "Bacaan",
                        backgroundColor = Color(0xFF1E88E5),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.STORY_LIST) }
                    )
                    QuickStatBadge(
                        iconText = "⭐",
                        countText = "$totalStars",
                        labelText = "Bintang",
                        backgroundColor = Color(0xFFFFA000),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.PROGRESS_DASHBOARD) }
                    )
                    QuickStatBadge(
                        iconText = "🏆",
                        countText = "$trophyCount Piala",
                        labelText = "Penghargaan",
                        backgroundColor = Color(0xFF8E24AA),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.TROPHIES) }
                    )
                }
            }
        }

        // 4. Main Activity Buttons Grid
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "Pilih Aktivitas Literasi",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BigActivityButton(
                        title = "Mulai Membaca",
                        subtitle = "10 Cerita Panjang",
                        icon = Icons.Default.MenuBook,
                        gradientColors = listOf(Color(0xFF1E88E5), Color(0xFF1565C0)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_start_reading",
                        onClick = { viewModel.navigateTo(ScreenRoute.STORY_LIST) }
                    )
                    BigActivityButton(
                        title = "Main Game",
                        subtitle = "10 Game Edukasi",
                        icon = Icons.Default.Games,
                        gradientColors = listOf(Color(0xFF43A047), Color(0xFF2E7D32)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_play_games",
                        onClick = { viewModel.navigateTo(ScreenRoute.GAMES_MENU) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BigActivityButton(
                        title = "Tantangan Soal",
                        subtitle = "100 Soal Analisis",
                        icon = Icons.Default.Quiz,
                        gradientColors = listOf(Color(0xFFFF9800), Color(0xFFE65100)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_do_quiz",
                        onClick = {
                            val activeStory = readingProgress.firstOrNull { !it.isCompleted }?.storyId ?: 1
                            viewModel.startQuizForStory(activeStory)
                        }
                    )
                    BigActivityButton(
                        title = "Tonton Video",
                        subtitle = "Literasi Interaktif",
                        icon = Icons.Default.VideoLibrary,
                        gradientColors = listOf(Color(0xFFE91E63), Color(0xFFC2185B)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_watch_video",
                        onClick = { viewModel.navigateTo(ScreenRoute.VIDEOS) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BigActivityButton(
                        title = "Bernyanyi",
                        subtitle = "5 Lagu Literasi",
                        icon = Icons.Default.MusicNote,
                        gradientColors = listOf(Color(0xFF00ACC1), Color(0xFF00838F)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_sing_song",
                        onClick = { viewModel.navigateTo(ScreenRoute.SONGS) }
                    )
                    BigActivityButton(
                        title = "Lihat Piala",
                        subtitle = "Trofi & Lencana",
                        icon = Icons.Default.EmojiEvents,
                        gradientColors = listOf(Color(0xFF8E24AA), Color(0xFF6A1B9A)),
                        modifier = Modifier.weight(1f),
                        testTag = "home_btn_view_trophies",
                        onClick = { viewModel.navigateTo(ScreenRoute.TROPHIES) }
                    )
                }
            }
        }

        // 5. Secondary Quick Menu Row (Tujuan Pembelajaran, Motivasi, Guru)
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                Text(
                    text = "Menu Pembelajaran Lainnya",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SmallMenuCard(
                        title = "Tujuan Belajar",
                        icon = Icons.Default.TrackChanges,
                        color = Color(0xFF0288D1),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.LEARNING_OBJECTIVES) }
                    )
                    SmallMenuCard(
                        title = "Motivasi",
                        icon = Icons.Default.Lightbulb,
                        color = Color(0xFFF57C00),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.MOTIVATION) }
                    )
                    SmallMenuCard(
                        title = "Laporan Guru",
                        icon = Icons.Default.School,
                        color = Color(0xFF388E3C),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.TEACHER_REPORT) }
                    )
                }
            }
        }

        // 6. Daily Motivation Snippet
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clickable { viewModel.navigateTo(ScreenRoute.MOTIVATION) }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "💡", fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Kata Penyemangat Hari Ini",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = "\"${dailyQuote.quote}\"",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BigActivityButton(
    title: String,
    subtitle: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier,
    testTag: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .height(96.dp)
            .clickable { onClick() }
            .testTag(testTag)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.horizontalGradient(gradientColors))
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SmallMenuCard(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                ),
                maxLines = 1
            )
        }
    }
}
