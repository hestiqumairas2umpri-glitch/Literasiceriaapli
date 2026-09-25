package com.example.ui.screens

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.ui.components.QuickStatBadge
import com.example.util.SoundManager

@Composable
fun ProfileScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.profileState.collectAsState()
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val gameProgress by viewModel.gameProgressState.collectAsState()
    val quizHistory by viewModel.quizHistoryState.collectAsState()

    var nameInput by remember(profile) { mutableStateOf(profile?.name ?: "Siswa Juara") }
    var classInput by remember(profile) { mutableStateOf(profile?.studentClass ?: "Kelas 6A") }
    var selectedAvatar by remember(profile) { mutableIntStateOf(profile?.avatarId ?: 0) }
    var selectedRole by remember(profile) { mutableStateOf(profile?.role ?: "SISWA") }

    val avatars = listOf("🦊 Kancil Cerdik", "🦉 Burung Hantu", "🐱 Kucing Petualang", "🚀 Astronot Cilik", "🐰 Kelinci Pembaca", "🤖 Robot Sahabat")

    val completedStoriesCount = readingProgress.count { it.isCompleted }
    val totalStars = readingProgress.sumOf { it.starsEarned } + gameProgress.sumOf { it.starsEarned }
    val trophyCount = when {
        completedStoriesCount >= 10 -> 5
        completedStoriesCount >= 8 -> 4
        completedStoriesCount >= 5 -> 3
        completedStoriesCount >= 3 -> 2
        completedStoriesCount >= 1 -> 1
        else -> 0
    }
    val averageScore = if (quizHistory.isNotEmpty()) {
        quizHistory.map { it.score }.average().toInt()
    } else 0

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Profil Pengguna",
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
                .testTag("profile_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar Showcase Card
            item {
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = avatars[selectedAvatar.coerceIn(0, avatars.size - 1)].split(" ").first(),
                                fontSize = 48.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = nameInput,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )

                        Text(
                            text = "$classInput • ${if (selectedRole == "GURU") "Guru Pembimbing" else "Siswa Kelas 6 SD"}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            // Accomplishments Badges
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickStatBadge(
                        iconText = "📚",
                        countText = "$completedStoriesCount",
                        labelText = "Selesai",
                        backgroundColor = Color(0xFF1E88E5),
                        modifier = Modifier.weight(1f)
                    )
                    QuickStatBadge(
                        iconText = "⭐",
                        countText = "$totalStars",
                        labelText = "Bintang",
                        backgroundColor = Color(0xFFFFA000),
                        modifier = Modifier.weight(1f)
                    )
                    QuickStatBadge(
                        iconText = "🏆",
                        countText = "$trophyCount",
                        labelText = "Piala",
                        backgroundColor = Color(0xFF8E24AA),
                        modifier = Modifier.weight(1f)
                    )
                    QuickStatBadge(
                        iconText = "🎯",
                        countText = "$averageScore",
                        labelText = "Rata-Rata",
                        backgroundColor = Color(0xFF43A047),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Edit Profile Form
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Pilih Karakter Avatar Favoritmu",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            itemsIndexed(avatars) { idx, item ->
                                val isSelected = selectedAvatar == idx
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clickable {
                                            selectedAvatar = idx
                                            SoundManager.playClick()
                                        }
                                        .then(
                                            if (isSelected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)) else Modifier
                                        )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = item.split(" ").first(), fontSize = 28.sp)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = item.split(" ").drop(1).joinToString(" "),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nameInput,
                            onValueChange = { nameInput = it },
                            label = { Text("Nama Siswa") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = classInput,
                            onValueChange = { classInput = it },
                            label = { Text("Kelas") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Switch Role (Siswa / Guru)
                        Text(
                            text = "Mode Akun Pengguna:",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(
                                onClick = {
                                    selectedRole = "SISWA"
                                    SoundManager.playClick()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedRole == "SISWA") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (selectedRole == "SISWA") Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Siswa")
                            }

                            Button(
                                onClick = {
                                    selectedRole = "GURU"
                                    SoundManager.playClick()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedRole == "GURU") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (selectedRole == "GURU") Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Guru")
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                viewModel.updateProfile(nameInput, classInput, selectedAvatar, selectedRole)
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("btn_save_profile")
                        ) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = "Simpan")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Simpan Perubahan Profil", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
