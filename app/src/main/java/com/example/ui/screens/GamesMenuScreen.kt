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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.GamesData
import com.example.data.model.LiteracyGameInfo
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.ui.components.StarRatingRow

@Composable
fun GamesMenuScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val gameProgress by viewModel.gameProgressState.collectAsState()

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Petualangan Games Literasi",
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
                .testTag("games_menu_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Game Hero Banner
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_petualangan_game),
                            contentDescription = "Petualangan Game Banner",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.8f)
                                        )
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "10 Game Edukasi Interaktif 🎮",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = "Kumpulkan poin, bintang ⭐, dan piala 🏆 di setiap tantangan!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            )
                        }
                    }
                }
            }

            // 10 Games list
            items(GamesData.gamesList) { game ->
                val progress = gameProgress.find { it.gameId == game.id }
                GameCardItem(
                    game = game,
                    isCompleted = progress?.isCompleted == true,
                    stars = progress?.starsEarned ?: 0,
                    highestScore = progress?.highestScore ?: 0,
                    trophyEarned = progress?.trophyEarned == true,
                    onPlayClick = { viewModel.selectGame(game.id) }
                )
            }
        }
    }
}

@Composable
fun GameCardItem(
    game: LiteracyGameInfo,
    isCompleted: Boolean,
    stars: Int,
    highestScore: Int,
    trophyEarned: Boolean,
    onPlayClick: () -> Unit
) {
    val themeColor = Color(game.themeColorHex)

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPlayClick() }
            .testTag("game_card_${game.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(themeColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = game.iconEmoji, fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = themeColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Game ${game.id}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = themeColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    if (trophyEarned) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "🏆 Juara", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFFA000))
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = game.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    ),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(6.dp))

                if (isCompleted) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StarRatingRow(starsEarned = stars, starSize = 16)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Skor: $highestScore",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = themeColor
                        )
                    }
                }
            }

            Button(
                onClick = onPlayClick,
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                modifier = Modifier.testTag("btn_play_game_${game.id}")
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Main", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = if (isCompleted) "Main Lagi" else "Mulai", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}
