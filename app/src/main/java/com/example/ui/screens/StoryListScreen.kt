package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.database.ReadingProgressEntity
import com.example.data.model.ReadingStory
import com.example.data.model.StoriesProvider
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.ui.components.StarRatingRow

@Composable
fun StoryListScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val readingProgress by viewModel.readingProgressState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedTheme by remember { mutableStateOf("Semua") }

    val themes = listOf("Semua", "Misteri & Petualangan", "Lingkungan & Sains", "Kebudayaan & Toleransi", "Inspirasi & Cita-Cita", "Fantasi & Literasi")

    val filteredStories = StoriesProvider.allStories.filter { story ->
        val matchesQuery = story.title.contains(searchQuery, ignoreCase = true) ||
                story.synopsis.contains(searchQuery, ignoreCase = true)
        val matchesTheme = selectedTheme == "Semua" || story.themeTag.contains(selectedTheme, ignoreCase = true)
        matchesQuery && matchesTheme
    }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Perpustakaan Cerita",
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
                .testTag("story_list_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Info
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "📚", fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Koleksi 10 Cerita Pilihan",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = "Teks panjang berkualitas khusus kelas 6 SD. Selesaikan bacaan untuk membuka tantangan soal!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                                )
                            )
                        }
                    }
                }
            }

            // Theme Filter Chips
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(themes) { theme ->
                        val isSelected = selectedTheme == theme
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedTheme = theme },
                            label = { Text(text = theme, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // 10 Story Cards
            items(filteredStories) { story ->
                val progress = readingProgress.find { it.storyId == story.id }
                val isUnlocked = viewModel.isStoryUnlocked(story.id, readingProgress)
                StoryCardItem(
                    story = story,
                    progress = progress,
                    isUnlocked = isUnlocked,
                    onOpenStory = { viewModel.openStory(story.id) }
                )
            }
        }
    }
}

@Composable
fun StoryCardItem(
    story: ReadingStory,
    progress: ReadingProgressEntity?,
    isUnlocked: Boolean,
    onOpenStory: () -> Unit
) {
    val isCompleted = progress?.isCompleted == true
    val bestScore = progress?.bestScore ?: 0
    val stars = progress?.starsEarned ?: 0

    val accentColor = when (story.id % 5) {
        1 -> Color(0xFF1E88E5)
        2 -> Color(0xFF43A047)
        3 -> Color(0xFFFF9800)
        4 -> Color(0xFF8E24AA)
        else -> Color(0xFFE91E63)
    }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else Color(0xFFF1F5F9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 3.dp else 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isUnlocked) { onOpenStory() }
            .testTag("story_card_${story.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = accentColor.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Bacaan ${story.id} • ${story.themeTag}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                if (isCompleted) {
                    Surface(
                        color = Color(0xFFE8F5E9),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Selesai",
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Selesai ($bestScore)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }
                } else if (!isUnlocked) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Terkunci",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Terkunci", fontSize = 11.sp, color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = story.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                )
            )

            Text(
                text = story.subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = story.synopsis,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f)
                ),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Durasi",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "±${story.estimatedMinutes} menit",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (isCompleted) {
                        Spacer(modifier = Modifier.width(12.dp))
                        StarRatingRow(starsEarned = stars, starSize = 16)
                    }
                }

                Button(
                    onClick = onOpenStory,
                    enabled = isUnlocked,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("btn_read_story_${story.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Mulai",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isCompleted) "Baca Ulang" else "Mulai Membaca",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
