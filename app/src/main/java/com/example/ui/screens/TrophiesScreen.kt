package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Badge
import com.example.data.model.TrophiesAndBadgesData
import com.example.data.model.Trophy
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar

@Composable
fun TrophiesScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val completedCount = readingProgress.count { it.isCompleted }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Piala Juara Membaca",
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
                .testTag("trophies_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🏆", fontSize = 36.sp)
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "Ruang Kehormatan Juara",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = "Raih 5 piala bergengsi dan 6 lencana kehormatan literasi!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                                )
                            )
                        }
                    }
                }
            }

            // Section: Trophies
            item {
                Text(
                    text = "🏆 5 Piala Tingkat Membaca",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            items(TrophiesAndBadgesData.trophies) { trophy ->
                val isUnlocked = completedCount >= trophy.targetCompletedStories
                TrophyCardItem(
                    trophy = trophy,
                    isUnlocked = isUnlocked,
                    currentCompleted = completedCount
                )
            }

            // Section: Badges
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "🎖️ 6 Lencana Keahlian Literasi",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            items(TrophiesAndBadgesData.badges) { badge ->
                val isBadgeUnlocked = when (badge.id) {
                    "badge_rajin" -> completedCount >= 1
                    "badge_ide_pokok" -> completedCount >= 2
                    "badge_detektif" -> completedCount >= 3
                    "badge_kosakata" -> completedCount >= 4
                    "badge_penakluk" -> completedCount >= 6
                    else -> completedCount >= 2 // Juara Game
                }
                BadgeCardItem(badge = badge, isUnlocked = isBadgeUnlocked)
            }
        }
    }
}

@Composable
fun TrophyCardItem(
    trophy: Trophy,
    isUnlocked: Boolean,
    currentCompleted: Int
) {
    val rankColor = Color(trophy.rankColorHex)

    ElevatedCard(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else Color(0xFFF1F5F9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 3.dp else 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(if (isUnlocked) rankColor.copy(alpha = 0.15f) else Color(0xFFE2E8F0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isUnlocked) trophy.iconEmoji else "🔒",
                    fontSize = 30.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = trophy.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray,
                            fontSize = 15.sp
                        )
                    )

                    if (isUnlocked) {
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Diraih ✅",
                                color = Color(0xFF2E7D32),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "$currentCompleted / ${trophy.targetCompletedStories}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = trophy.requirementDescription,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    fontSize = 12.sp
                )

                if (!isUnlocked) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val progress = (currentCompleted.toFloat() / trophy.targetCompletedStories).coerceIn(0f, 1f)
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = rankColor,
                        trackColor = Color(0xFFE2E8F0)
                    )
                }
            }
        }
    }
}

@Composable
fun BadgeCardItem(
    badge: Badge,
    isUnlocked: Boolean
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) Color(0xFFFFFDE7) else Color(0xFFF8FAFC)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(if (isUnlocked) Color(0xFFFFECB3) else Color(0xFFE2E8F0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isUnlocked) badge.iconEmoji else "🔒",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = badge.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isUnlocked) Color(0xFFE65100) else Color.Gray
                    )
                )
                Text(
                    text = badge.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            if (isUnlocked) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Aktif",
                    tint = Color(0xFF43A047),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
