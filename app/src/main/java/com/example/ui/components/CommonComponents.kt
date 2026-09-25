package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.YellowGold
import com.example.util.SoundManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiteracyTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onHomeClick: (() -> Unit)? = null,
    onToggleFontSize: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("app_bar_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        actions = {
            if (onToggleFontSize != null) {
                IconButton(
                    onClick = onToggleFontSize,
                    modifier = Modifier.testTag("app_bar_font_size_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.FormatSize,
                        contentDescription = "Perbesar Teks",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            if (onHomeClick != null) {
                IconButton(
                    onClick = onHomeClick,
                    modifier = Modifier.testTag("app_bar_home_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Beranda",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}

@Composable
fun StarRatingRow(
    starsEarned: Int,
    maxStars: Int = 3,
    starSize: Int = 24,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            Icon(
                imageVector = if (i <= starsEarned) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = "Bintang $i",
                tint = if (i <= starsEarned) YellowGold else Color.LightGray,
                modifier = Modifier.size(starSize.dp)
            )
        }
    }
}

@Composable
fun QuickStatBadge(
    iconText: String,
    countText: String,
    labelText: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = iconText,
                fontSize = 26.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = countText,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                )
                Text(
                    text = labelText,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.White.copy(alpha = 0.9f)
                    )
                )
            }
        }
    }
}

@Composable
fun FeedbackDialog(
    isCorrect: Boolean,
    explanation: String,
    onDismissOrNext: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissOrNext,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (isCorrect) "🌟 Hebat! Jawabanmu Benar!" else "💡 Hampir Benar!",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isCorrect) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
                    )
                )
            }
        },
        text = {
            Column {
                Text(
                    text = if (isCorrect) {
                        "Kamu luar biasa! Terus pertahankan semangat membacamu."
                    } else {
                        "Yuk, baca kembali bagian tersebut dengan teliti. Kamu pasti bisa!"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = explanation,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismissOrNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isCorrect) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.testTag("feedback_next_button")
            ) {
                Text(text = "Lanjut Soal Berikutnya", fontWeight = FontWeight.Bold)
            }
        },
        shape = RoundedCornerShape(20.dp)
    )
}
