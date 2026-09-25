package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StoriesProvider
import com.example.ui.LiterasiViewModel
import com.example.ui.components.LiteracyTopAppBar
import com.example.util.SoundManager

@Composable
fun TeacherReportScreen(
    viewModel: LiterasiViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val profile by viewModel.profileState.collectAsState()
    val readingProgress by viewModel.readingProgressState.collectAsState()
    val gameProgress by viewModel.gameProgressState.collectAsState()
    val quizHistory by viewModel.quizHistoryState.collectAsState()

    var showPrintDialog by remember { mutableStateOf(false) }

    val completedStoriesCount = readingProgress.count { it.isCompleted }
    val totalQuestionsAnswered = quizHistory.size * 10
    val averageScore = if (quizHistory.isNotEmpty()) {
        quizHistory.map { it.score }.average().toInt()
    } else 0
    val completedGamesCount = gameProgress.count { it.isCompleted }
    val totalStars = readingProgress.sumOf { it.starsEarned } + gameProgress.sumOf { it.starsEarned }
    val trophyCount = when {
        completedStoriesCount >= 10 -> 5
        completedStoriesCount >= 8 -> 4
        completedStoriesCount >= 5 -> 3
        completedStoriesCount >= 3 -> 2
        completedStoriesCount >= 1 -> 1
        else -> 0
    }
    val readingMinutes = profile?.totalReadingMinutes ?: 0

    // Diagnosis of competencies needing practice
    val lowScoreQuizzes = quizHistory.filter { it.score < 70 }
    val needPracticeTopics = if (lowScoreQuizzes.isNotEmpty()) {
        lowScoreQuizzes.map { "Evaluasi Pemahaman: ${it.storyTitle}" }
    } else if (completedStoriesCount < 3) {
        listOf("Kelancaran Membaca Teks Panjang", "Penentuan Ide Pokok Paragraf Awal", "Penguasaan Kosakata Sains")
    } else {
        listOf("Semua indikator dasar telah tercapai dengan sangat baik!")
    }

    Scaffold(
        topBar = {
            LiteracyTopAppBar(
                title = "Laporan Untuk Guru",
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
                .testTag("teacher_report_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Info Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = "Guru",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Lembar Laporan Hasil Literasi Siswa",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Nama Siswa: ${profile?.name ?: "Siswa Juara"} • ${profile?.studentClass ?: "Kelas 6 SD"}",
                                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = {
                                    SoundManager.playClick()
                                    showPrintDialog = true
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("btn_print_report")
                            ) {
                                Icon(imageVector = Icons.Default.Print, contentDescription = "Cetak")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Cetak Laporan", fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = {
                                    SoundManager.playClick()
                                    val shareText = """
                                        === LAPORAN LITERASI SISWA KELAS 6 ===
                                        Nama: ${profile?.name ?: "Siswa"}
                                        Kelas: ${profile?.studentClass ?: "6 SD"}
                                        Bacaan Selesai: $completedStoriesCount dari 10
                                        Nilai Rata-Rata: $averageScore
                                        Total Bintang: $totalStars
                                        Piala Diperoleh: $trophyCount
                                        Lama Membaca: $readingMinutes menit
                                    """.trimIndent()
                                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                        putExtra(Intent.EXTRA_TEXT, shareText)
                                        type = "text/plain"
                                    }
                                    context.startActivity(Intent.createChooser(sendIntent, "Unduh/Bagikan Laporan"))
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("btn_download_report")
                            ) {
                                Icon(imageVector = Icons.Default.Download, contentDescription = "Unduh")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Unduh Laporan", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            // Summary Table Card
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Ringkasan Hasil Belajar",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        ReportRowItem("Jumlah Bacaan Selesai", "$completedStoriesCount dari 10 Bacaan")
                        ReportRowItem("Jumlah Soal Dikerjakan", "$totalQuestionsAnswered Butir Soal")
                        ReportRowItem("Nilai Rata-Rata Kuis", "$averageScore / 100 Poin")
                        ReportRowItem("Game Literasi Selesai", "$completedGamesCount dari 10 Permainan")
                        ReportRowItem("Total Bintang Diperoleh", "⭐ $totalStars Bintang")
                        ReportRowItem("Piala Kehormatan", "🏆 $trophyCount Piala")
                        ReportRowItem("Durasi Membaca Aktif", "⏱️ $readingMinutes Menit")
                    }
                }
            }

            // Detailed Story Scores Table
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Tabel Rincian Nilai 10 Bacaan",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        // Table Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(text = "No", fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.width(28.dp))
                            Text(text = "Judul Bacaan", fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.weight(1f))
                            Text(text = "Status", fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.width(64.dp))
                            Text(text = "Skor", fontWeight = FontWeight.Bold, fontSize = 11.sp, modifier = Modifier.width(42.dp))
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        StoriesProvider.allStories.forEach { story ->
                            val progress = readingProgress.find { it.storyId == story.id }
                            val isDone = progress?.isCompleted == true
                            val score = progress?.bestScore ?: 0

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp, horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "${story.id}", fontSize = 12.sp, modifier = Modifier.width(28.dp))
                                Text(text = story.title, fontSize = 12.sp, modifier = Modifier.weight(1f), maxLines = 1)
                                Text(
                                    text = if (isDone) "Selesai ✅" else "Belum ⏳",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isDone) Color(0xFF2E7D32) else Color.Gray,
                                    modifier = Modifier.width(64.dp)
                                )
                                Text(
                                    text = if (isDone) "$score" else "-",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(42.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Section: Materi yang Masih Perlu Dilatih
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "📌", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Materi yang Perlu Pendampingan Latihan:",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE65100),
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        needPracticeTopics.forEach { topic ->
                            Row(
                                modifier = Modifier.padding(vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "• ", fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
                                Text(
                                    text = topic,
                                    fontSize = 12.sp,
                                    color = Color(0xFF5D4037)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Print Friendly Dialog Preview
    if (showPrintDialog) {
        AlertDialog(
            onDismissRequest = { showPrintDialog = false },
            title = {
                Text(text = "🖨️ Pratinjau Cetak Laporan Guru", fontWeight = FontWeight.Bold)
            },
            text = {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = """
                                KEMENTERIAN PENDIDIKAN, KEBUDAYAAN, RISET, DAN TEKNOLOGI
                                RAPOR PERKEMBANGAN LITERASI MEMBACA KELAS 6 SD
                                --------------------------------------------------
                                Nama Siswa : ${profile?.name ?: "Siswa"}
                                Kelas      : ${profile?.studentClass ?: "6 SD"}
                                Tanggal    : 2026-09-25
                                
                                HASIL CAPAIAN:
                                • Total Bacaan Tuntas : $completedStoriesCount dari 10
                                • Nilai Rata-Rata Kuis: $averageScore / 100
                                • Total Perolehan     : ⭐ $totalStars | 🏆 $trophyCount
                                • Catatan Guru        : Siswa menunjukkan kemajuan yang
                                  konsisten dalam memahami teks panjang dan kosakata.
                            """.trimIndent(),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPrintDialog = false
                        SoundManager.playCorrect()
                    }
                ) {
                    Text("Cetak / Simpan PDF")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showPrintDialog = false }) {
                    Text("Tutup")
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun ReportRowItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
