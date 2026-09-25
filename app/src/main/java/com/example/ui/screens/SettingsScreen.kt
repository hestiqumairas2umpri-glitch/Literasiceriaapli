package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.util.SoundManager

@Composable
fun SettingsScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val fontScale by viewModel.fontScale.collectAsState()
    val highContrast by viewModel.highContrastMode.collectAsState()

    var sfxState by remember { mutableStateOf(SoundManager.sfxEnabled) }
    var musicState by remember { mutableStateOf(SoundManager.musicEnabled) }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Pengaturan & Aksesibilitas",
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
                .testTag("settings_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Audio Settings Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Pengaturan Audio & Suara",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "Efek Suara",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(12.dp))
                                Column {
                                    Text(text = "Efek Suara (SFX)", fontWeight = FontWeight.Bold)
                                    Text(
                                        text = "Suara klik, jawaban benar, bintang, dan piala",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = sfxState,
                                onCheckedChange = {
                                    sfxState = it
                                    viewModel.toggleSfx()
                                },
                                modifier = Modifier.testTag("switch_sfx")
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MusicNote,
                                    contentDescription = "Musik",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(12.dp))
                                Column {
                                    Text(text = "Musik Lagu Literasi", fontWeight = FontWeight.Bold)
                                    Text(
                                        text = "Melodi ceria untuk bernyanyi bersama",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = musicState,
                                onCheckedChange = {
                                    musicState = it
                                    viewModel.toggleMusic()
                                },
                                modifier = Modifier.testTag("switch_music")
                            )
                        }
                    }
                }
            }

            // Accessibility & Display Settings Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Aksesibilitas & Tampilan Teks",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(
                            text = "Ukuran Huruf Bacaan:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = { viewModel.setFontScale(1.0f) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (fontScale == 1.0f) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (fontScale == 1.0f) Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Normal")
                            }

                            Button(
                                onClick = { viewModel.setFontScale(1.2f) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (fontScale == 1.2f) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (fontScale == 1.2f) Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Sedang (A+)")
                            }

                            Button(
                                onClick = { viewModel.setFontScale(1.4f) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (fontScale == 1.4f) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (fontScale == 1.4f) Color.White else MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Besar (A++)")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Contrast,
                                    contentDescription = "Kontras",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(12.dp))
                                Column {
                                    Text(text = "Mode Kontras Tinggi", fontWeight = FontWeight.Bold)
                                    Text(
                                        text = "Memperjelas teks bacaan untuk kenyamanan mata",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = highContrast,
                                onCheckedChange = { viewModel.toggleHighContrast() },
                                modifier = Modifier.testTag("switch_high_contrast")
                            )
                        }
                    }
                }
            }

            // About App Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Tentang",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "Tentang Aplikasi",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Petualangan Literasi: Jelajah Dunia Membaca",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Tagline: Baca, Pahami, Bermain, dan Jadilah Juara!",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Aplikasi media pembelajaran interaktif berbasis digital yang didesain khusus untuk siswa kelas 6 Sekolah Dasar (SD) guna meningkatkan kemampuan pemahaman bacaan panjang, ide pokok, kosakata, dan penalaran kritis.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}
