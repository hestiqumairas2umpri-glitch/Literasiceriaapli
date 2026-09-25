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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.util.SoundManager
import kotlinx.coroutines.delay

@Composable
fun StoryReaderScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val story by viewModel.selectedStory.collectAsState()
    val fontScale by viewModel.fontScale.collectAsState()

    var secondsElapsed by remember { mutableIntStateOf(0) }
    var selectedVocabWord by remember { mutableStateOf<Pair<String, String>?>(null) }

    // Reading Timer
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            secondsElapsed++
        }
    }

    if (story == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Bacaan tidak ditemukan.")
        }
        return
    }

    val currentStory = story!!
    val totalParagraphs = currentStory.paragraphs.size

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Bacaan ${currentStory.id}: ${currentStory.title}",
                onBackClick = { viewModel.navigateBack() },
                onToggleFontSize = {
                    val nextScale = when (fontScale) {
                        1.0f -> 1.2f
                        1.2f -> 1.4f
                        else -> 1.0f
                    }
                    viewModel.setFontScale(nextScale)
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Waktu",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        val mins = secondsElapsed / 60
                        val secs = secondsElapsed % 60
                        Text(
                            text = String.format("%02d:%02d", mins, secs),
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    Button(
                        onClick = {
                            SoundManager.playLevelUp()
                            viewModel.startQuizForStory(currentStory.id)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.testTag("btn_finish_reading")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = "Mulai Soal",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Selesai & Jawab Soal",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .testTag("story_reader_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Story Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = currentStory.themeTag,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            // Font size controls
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                TextButton(
                                    onClick = { viewModel.setFontScale(1.0f) },
                                    modifier = Modifier.testTag("btn_font_normal")
                                ) {
                                    Text(
                                        text = "A",
                                        fontWeight = if (fontScale == 1.0f) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                }
                                TextButton(
                                    onClick = { viewModel.setFontScale(1.2f) },
                                    modifier = Modifier.testTag("btn_font_medium")
                                ) {
                                    Text(
                                        text = "A+",
                                        fontWeight = if (fontScale == 1.2f) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                }
                                TextButton(
                                    onClick = { viewModel.setFontScale(1.4f) },
                                    modifier = Modifier.testTag("btn_font_large")
                                ) {
                                    Text(
                                        text = "A++",
                                        fontWeight = if (fontScale == 1.4f) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = currentStory.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = (22 * fontScale).sp
                            )
                        )

                        Text(
                            text = currentStory.subtitle,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = (14 * fontScale).sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Sinopsis: ${currentStory.synopsis}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = (12 * fontScale).sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }

            // Paragraphs with clear paragraph markers
            itemsIndexed(currentStory.paragraphs) { index, paragraph ->
                ParagraphCard(
                    paragraphNumber = index + 1,
                    text = paragraph,
                    fontScale = fontScale
                )
            }

            // Vocabulary List Section
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "💡", fontSize = 22.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Kamus Kosakata Penting",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFE65100)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ketuk kata di bawah untuk mempelajari artinya:",
                            fontSize = 12.sp,
                            color = Color(0xFF5D4037)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        currentStory.keyVocabulary.forEach { (word, definition) ->
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { selectedVocabWord = Pair(word, definition) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "📖 $word",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = (13 * fontScale).sp,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.width(130.dp)
                                    )
                                    Text(
                                        text = definition,
                                        fontSize = (12 * fontScale).sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Main Idea & Moral Message Card
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📌 Inti Ide Pokok Cerita:",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = currentStory.mainIdea,
                            fontSize = (13 * fontScale).sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "🌱 Pesan Moral / Amanat:",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        )
                        Text(
                            text = currentStory.moralMessage,
                            fontSize = (13 * fontScale).sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            // Spacing for bottom bar
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

    // Vocabulary Dialog
    selectedVocabWord?.let { (word, definition) ->
        AlertDialog(
            onDismissRequest = { selectedVocabWord = null },
            title = {
                Text(
                    text = "Kosakata: $word",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(
                    text = definition,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(onClick = { selectedVocabWord = null }) {
                    Text("Paham!")
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun ParagraphCard(
    paragraphNumber: Int,
    text: String,
    fontScale: Float
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Paragraf $paragraphNumber",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = (15 * fontScale).sp,
                    lineHeight = (24 * fontScale).sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}
