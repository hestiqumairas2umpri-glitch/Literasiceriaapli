package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LiteracyLightColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    primaryContainer = BlueLight,
    onPrimaryContainer = BlueDark,

    secondary = OrangeSecondary,
    onSecondary = Color.White,
    secondaryContainer = OrangeLight,
    onSecondaryContainer = OrangeDark,

    tertiary = GreenTertiary,
    onTertiary = Color.White,
    tertiaryContainer = GreenLight,
    onTertiaryContainer = GreenTertiary,

    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceLight,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = TextSecondary,
    error = Color(0xFFD32F2F),
    onError = Color.White
)

private val LiteracyHighContrastColorScheme = darkColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF1976D2),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFFFFB74D),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFF57C00),
    onSecondaryContainer = Color.White,

    tertiary = Color(0xFF81C784),
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF388E3C),
    onTertiaryContainer = Color.White,

    background = BackgroundHighContrast,
    onBackground = TextHighContrast,
    surface = SurfaceHighContrast,
    onSurface = TextHighContrast,
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFFE2E8F0),
    error = Color(0xFFEF5350),
    onError = Color.Black
)

@Composable
fun PetualanganLiterasiTheme(
    highContrastMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (highContrastMode) {
        LiteracyHighContrastColorScheme
    } else {
        LiteracyLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
