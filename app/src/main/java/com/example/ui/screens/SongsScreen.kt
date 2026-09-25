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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.example.data.model.SongsData
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.util.SoundManager

@Composable
fun SongsScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val currentSongIdx by viewModel.currentSongIndex.collectAsState()
    val isPlaying by viewModel.isSongPlaying.collectAsState()
    val activeLyricIdx by viewModel.currentLyricLineIndex.collectAsState()

    var singAlongMode by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(SoundManager.volumeLevel) }

    val currentSong = SongsData.songs.getOrNull(currentSongIdx) ?: SongsData.songs.first()

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Lagu Literasi Anak",
                onBackClick = {
                    viewModel.stopSongPlayback()
                    viewModel.navigateBack()
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .testTag("songs_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Song Selector Tabs
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(SongsData.songs) { index, song ->
                        val isSelected = currentSongIdx == index
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            shadowElevation = if (isSelected) 3.dp else 1.dp,
                            modifier = Modifier
                                .clickable { viewModel.selectSong(index) }
                                .testTag("song_tab_$index")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MusicNote,
                                    contentDescription = null,
                                    tint = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = song.title,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Player Console Card
            item {
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Musical Vinyl Animation Disk
                        Box(
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primaryContainer,
                                            MaterialTheme.colorScheme.primary
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.GraphicEq else Icons.Default.MusicNote,
                                contentDescription = "Irama",
                                tint = Color.White,
                                modifier = Modifier.size(44.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = currentSong.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                        Text(
                            text = currentSong.themeDescription,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Controls: Play/Pause, Stop, Sing Along
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            IconButton(
                                onClick = { viewModel.stopSongPlayback() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .testTag("btn_stop_song")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Stop,
                                    contentDescription = "Berhenti",
                                    tint = Color.Red
                                )
                            }

                            Button(
                                onClick = { viewModel.togglePlaySong() },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = CircleShape,
                                modifier = Modifier
                                    .size(64.dp)
                                    .testTag("btn_play_song")
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isPlaying) "Jeda" else "Putar",
                                    modifier = Modifier.size(36.dp)
                                )
                            }

                            IconButton(
                                onClick = {
                                    singAlongMode = !singAlongMode
                                    SoundManager.playClick()
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (singAlongMode) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
                                    )
                                    .testTag("btn_sing_along_mode")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Bernyanyi Bersama",
                                    tint = if (singAlongMode) MaterialTheme.colorScheme.secondary else Color.DarkGray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Volume Control Slider
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeDown,
                                contentDescription = "Kecil",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                            Slider(
                                value = volume,
                                onValueChange = {
                                    volume = it
                                    SoundManager.volumeLevel = it
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Besar",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            // Synchronized Karaoke Lyrics Display
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (singAlongMode) Color(0xFFFFFDE7) else MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (singAlongMode) "🎤 Mode Bernyanyi Bersama" else "📜 Lirik Berjalan (Karaoke)",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (singAlongMode) Color(0xFFE65100) else MaterialTheme.colorScheme.primary
                                )
                            )

                            if (isPlaying) {
                                Surface(
                                    color = Color(0xFFE8F5E9),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "Sedang Berputar 🎵",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF2E7D32),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        currentSong.lyricsLines.forEachIndexed { index, line ->
                            val isCurrentLine = isPlaying && activeLyricIdx == index

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isCurrentLine) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (isCurrentLine) {
                                        Text(text = "▶ ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                    }
                                    Text(
                                        text = line.lineText,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = if (isCurrentLine) FontWeight.ExtraBold else FontWeight.Normal,
                                            fontSize = if (isCurrentLine) 16.sp else 14.sp,
                                            color = if (isCurrentLine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                        )
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
