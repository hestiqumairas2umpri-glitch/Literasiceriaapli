package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LearningObjective
import com.example.data.model.LearningObjectivesData
import com.example.data.model.ObjectiveIconType
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar

@Composable
fun LearningObjectivesScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val completedCount = readingProgress.count { it.isCompleted }

    // Overall mastery calculation (0..100%)
    val masteryPercent = ((completedCount.toFloat() / 10f) * 100).toInt().coerceIn(0, 100)

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Tujuan Pembelajaran",
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
                .testTag("learning_objectives_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🎯", fontSize = 28.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Target Capaian Literasi Kelas 6",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                )
                                Text(
                                    text = "Kemampuan membaca & bernalar kritis",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Tingkat Penguasaan Kompetensi",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "$masteryPercent%",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { masteryPercent / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = Color.White.copy(alpha = 0.5f)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (completedCount >= 8) {
                                "🌟 Luar biasa! Hampir seluruh kompetensi literasi telah kamu kuasai!"
                            } else if (completedCount >= 4) {
                                "🚀 Hebat! Terus membaca dan berlatih soal untuk melengkapi penguasaanmu."
                            } else {
                                "🌱 Yuk mulai petualangan membaca untuk mengasah setiap target di bawah ini!"
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }

            // 10 Interactive Objective Cards
            itemsIndexed(LearningObjectivesData.objectives) { index, objective ->
                // An objective is mastered if sufficient stories completed
                val isMastered = completedCount >= (index + 1)
                ObjectiveCard(
                    objective = objective,
                    index = index + 1,
                    isMastered = isMastered
                )
            }
        }
    }
}

@Composable
fun ObjectiveCard(
    objective: LearningObjective,
    index: Int,
    isMastered: Boolean
) {
    var isExpanded by remember { mutableStateOf(false) }

    val iconVector: ImageVector = when (objective.iconType) {
        ObjectiveIconType.BOOK -> Icons.Default.MenuBook
        ObjectiveIconType.IDEA_LAMP -> Icons.Default.Lightbulb
        ObjectiveIconType.MAGNIFIER -> Icons.Default.Search
        ObjectiveIconType.PENCIL -> Icons.Default.MenuBook
        ObjectiveIconType.STAR -> Icons.Default.Star
        ObjectiveIconType.TROPHY -> Icons.Default.EmojiEvents
    }

    val iconColor = when (objective.iconType) {
        ObjectiveIconType.BOOK -> Color(0xFF1E88E5)
        ObjectiveIconType.IDEA_LAMP -> Color(0xFFFFA000)
        ObjectiveIconType.MAGNIFIER -> Color(0xFF8E24AA)
        ObjectiveIconType.PENCIL -> Color(0xFF00ACC1)
        ObjectiveIconType.STAR -> Color(0xFFE91E63)
        ObjectiveIconType.TROPHY -> Color(0xFF43A047)
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isMastered) Color(0xFFF1F8E9) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(iconColor.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = iconVector,
                            contentDescription = objective.title,
                            tint = iconColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = iconColor.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Target $index",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = iconColor,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            if (isMastered) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Tercapai",
                                    tint = Color(0xFF43A047),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Text(
                            text = objective.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            fontSize = 14.sp
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Rincian",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 10.dp, start = 54.dp)) {
                    Text(
                        text = objective.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Kategori: ${objective.category}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Surface(
                            color = if (isMastered) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (isMastered) "Status: Dikuasai ✅" else "Status: Sedang Berlatih ⏳",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isMastered) Color(0xFF2E7D32) else Color(0xFFE65100),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
